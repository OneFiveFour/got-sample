package io.redandroid.gameofthrones.screens.houses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import io.redandroid.data.model.House
import io.redandroid.gameofthrones.R
import io.redandroid.gameofthrones.common.DataBindingAdapter
import io.redandroid.gameofthrones.common.DataBindingViewHolder
import io.redandroid.gameofthrones.common.ItemClickListener


/**
 * The Paging adapter for [House]s.
 *
 * This adapter is using the Jetpack Paging library 3 and works together with [androidx.paging.PagingData]
 * Use [submitData] to set a new list of items. They are compared using the provided DiffCallback.
 */
class HousesAdapter(private val clickListener: ItemClickListener) : PagingDataAdapter<House, DataBindingViewHolder<House>>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<House> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return DataBindingViewHolder(binding)
    }

    override fun getItemViewType(position: Int) = R.layout.item_house

    override fun onBindViewHolder(holder: DataBindingViewHolder<House>, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item, clickListener)
    }

    /**
     * The DiffCallback is used by the [DataBindingAdapter] to check which items are
     * completely new and which just changed its content. When defining the Boolean checks, be
     * as precise as possible to avoid strange behaviour of the RecyclerView items.
     */
    object DiffCallback : DiffUtil.ItemCallback<House>() {
        override fun areItemsTheSame(oldItem: House, newItem: House): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: House, newItem: House): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
