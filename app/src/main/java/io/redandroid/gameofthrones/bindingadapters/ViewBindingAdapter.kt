package io.redandroid.gameofthrones.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter
import io.redandroid.gameofthrones.common.ItemClickListener

/**
 * Binding Adapter to set the given [ItemClickListener] to the clicked [view]
 * Along with the view, the [clickedItem] will be passed into the click listener.
 */
@BindingAdapter("clickListener", "clickedItem")
fun setClickListener(view: View, clickListener: ItemClickListener, clickedItem: Any) {
    view.setOnClickListener {
        clickListener.onItemClicked(view, clickedItem)
    }
}