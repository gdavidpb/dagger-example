package com.gdavidpb.daggerexample.ui.activities

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdavidpb.daggerexample.R
import com.gdavidpb.daggerexample.utils.isNetworkAvailable
import com.gdavidpb.daggerexample.utils.notNull
import com.gdavidpb.daggerexample.utils.observe
import com.gdavidpb.daggerexample.domain.model.Data
import com.gdavidpb.daggerexample.domain.model.DataState
import com.gdavidpb.daggerexample.domain.model.Post
import com.gdavidpb.daggerexample.presentation.viewmodel.MainActivityViewModel
import com.gdavidpb.daggerexample.ui.adapters.PostAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val connectivityManager: ConnectivityManager by inject()

    private val mainActivityViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rViewList.apply {
            layoutManager = LinearLayoutManager(context)

            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        with(mainActivityViewModel) {
            observe(posts, ::onPostsLoaded)

            getPosts()

            sLayoutList.onRefresh { getPosts() }
        }
    }

    private fun onPostsLoaded(event: Data<List<Post>>?) {
        event ?: return

        with(event) {
            when (dataState) {
                DataState.LOADING -> sLayoutList.isRefreshing = true
                DataState.ERROR -> sLayoutList.isRefreshing = false
                DataState.SUCCESS -> sLayoutList.isRefreshing = false
            }

            data.notNull {
                if (it.isNotEmpty()) {
                    val adapter = PostAdapter(source = it)

                    rViewList.swapAdapter(adapter, false)

                    tViewListEmpty.visibility = View.GONE
                } else
                    tViewListEmpty.visibility = View.VISIBLE
            }

            exception.notNull {
                if (connectivityManager.isNetworkAvailable())
                    toast(R.string.toast_error)
                else
                    toast(R.string.toast_no_connection)
            }
        }
    }
}
