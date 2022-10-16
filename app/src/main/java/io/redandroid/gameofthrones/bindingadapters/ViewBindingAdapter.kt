package io.redandroid.gameofthrones.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter
import io.redandroid.gameofthrones.common.ItemClickListener

@BindingAdapter("clickListener", "clickedItem")
fun setClickListener(view: View, clickListener: ItemClickListener, clickedItem: Any) {
    view.setOnClickListener {
        clickListener.onItemClicked(view, clickedItem)
    }
}