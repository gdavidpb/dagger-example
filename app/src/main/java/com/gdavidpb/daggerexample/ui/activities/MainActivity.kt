package com.gdavidpb.daggerexample.ui.activities

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdavidpb.daggerexample.R
import com.gdavidpb.daggerexample.domain.model.Post
import com.gdavidpb.daggerexample.domain.usecase.coroutines.Result
import com.gdavidpb.daggerexample.presentation.viewmodel.MainActivityViewModel
import com.gdavidpb.daggerexample.ui.adapters.PostAdapter
import com.gdavidpb.daggerexample.utils.isNetworkAvailable
import com.gdavidpb.daggerexample.utils.observe
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val connectivityManager: ConnectivityManager by inject()

    private val mainActivityViewModel: MainActivityViewModel by viewModel()

    private val postsAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rViewList.apply {
            layoutManager = LinearLayoutManager(context)

            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        with(mainActivityViewModel) {
            observe(posts, ::postsObserver)

            getPosts()

            sLayoutList.onRefresh { getPosts() }
        }
    }

    private fun postsObserver(result: Result<List<Post>>?) {
        when (result) {
            is Result.OnLoading -> {
                sLayoutList.isRefreshing = true
            }
            is Result.OnSuccess -> {
                sLayoutList.isRefreshing = false

                val posts = result.value

                if (posts.isNotEmpty()) {
                    tViewListEmpty.visibility = View.GONE
                    rViewList.visibility = View.VISIBLE

                    postsAdapter.swapItems(new = posts)
                } else {
                    rViewList.visibility = View.GONE
                    tViewListEmpty.visibility = View.VISIBLE
                }
            }
            is Result.OnError -> {
                sLayoutList.isRefreshing = false

                if (connectivityManager.isNetworkAvailable())
                    toast(R.string.toast_error)
                else
                    toast(R.string.toast_no_connection)
            }
            else -> {
                sLayoutList.isRefreshing = false
            }
        }
    }
}
