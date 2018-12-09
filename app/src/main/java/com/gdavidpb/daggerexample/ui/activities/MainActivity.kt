package com.gdavidpb.daggerexample.ui.activities

import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gdavidpb.daggerexample.R
import com.gdavidpb.daggerexample.data.di.ViewModelFactory
import com.gdavidpb.daggerexample.data.utils.isNetworkAvailable
import com.gdavidpb.daggerexample.data.utils.notNull
import com.gdavidpb.daggerexample.data.utils.observe
import com.gdavidpb.daggerexample.data.utils.withViewModel
import com.gdavidpb.daggerexample.domain.model.Data
import com.gdavidpb.daggerexample.domain.model.DataState
import com.gdavidpb.daggerexample.domain.model.Post
import com.gdavidpb.daggerexample.presentation.viewmodel.MainActivityViewModel
import com.gdavidpb.daggerexample.ui.adapters.PostAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rViewList.apply {
            layoutManager = LinearLayoutManager(context)

            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        withViewModel<MainActivityViewModel>(viewModelFactory) {
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
