package io.redandroid.gameofthrones.common.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.redandroid.gameofthrones.BR


/**
 * A generic ViewHolder that is used in connection with [DataBindingAdapter].
 * The passed item and the clickListener are automatically mapped to the data-binding variable called "item".
 * So make sure that you use this name in your xml layout if you want to make use of this ViewHolder.
 */
class DataBindingViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    var item: T? = null
        private set
    
    fun bind(item: T, clickListener: ItemClickListener) {
        this.item = item
        binding.setVariable(BR.item, item)
        binding.setVariable(BR.clickListener, clickListener)
        binding.executePendingBindings()
    }
    
}