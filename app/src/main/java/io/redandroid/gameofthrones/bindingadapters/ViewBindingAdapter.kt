package io.redandroid.gameofthrones.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter
import io.redandroid.gameofthrones.common.recyclerview.ItemClickListener

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

/**
 * Sets the loading animation to indicate network activity
 */
@BindingAdapter("loading")
fun isLoading(view: View, isLoading: Boolean?) {
    when (isLoading) {
        true -> view.fadeIn()
        else -> view.fadeOut()
    }
}

/**
 * A common binding adapter to use a boolean for hiding and showing a View.
 * This one is setting the View to GONE. Use "binding:isInvisible" to set it to INVISIBLE.
 */
@BindingAdapter("isVisible")
fun setVisibilityGone(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.fadeOut() {
    this.animate().alpha(0f).start()
}
fun View.fadeIn() {
    this.animate().alpha(1f).start()
}