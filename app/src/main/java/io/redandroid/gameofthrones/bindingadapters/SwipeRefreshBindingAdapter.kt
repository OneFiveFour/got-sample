package io.redandroid.gameofthrones.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


/**
 * Set the loading animation of a [SwipeRefreshLayout] using a [DataState].
 */
@BindingAdapter("refreshing")
fun isRefreshing(swipeRefreshLayout: SwipeRefreshLayout, loadState: LoadState?) {
    val isRefreshing = loadState == LoadState.Loading
    swipeRefreshLayout.isRefreshing = isRefreshing
}
