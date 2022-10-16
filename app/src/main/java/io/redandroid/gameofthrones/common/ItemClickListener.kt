package io.redandroid.gameofthrones.common

import android.view.View

/**
 * A ClickListener defining all click events that are possible on a list item.
 */
interface ItemClickListener {
    fun onItemClicked(clickedView: View, clickedItem: Any)
}