package io.redandroid.gameofthrones.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import timber.log.Timber


/**
 * Set the loading animation of a [SwipeRefreshLayout] using a [DataState].
 */
@BindingAdapter("refreshing")
fun isRefreshing(swipeRefreshLayout: SwipeRefreshLayout, loadState: LoadState?) {
    val isRefreshing = loadState == LoadState.Loading
    Timber.d("+++ is Refreshing: $isRefreshing")
    swipeRefreshLayout.isRefreshing = isRefreshing
}
