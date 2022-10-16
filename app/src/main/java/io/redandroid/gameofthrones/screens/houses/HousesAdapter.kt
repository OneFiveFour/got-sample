package io.redandroid.gameofthrones.screens.houses

import androidx.recyclerview.widget.DiffUtil
import io.redandroid.data.model.House
import io.redandroid.gameofthrones.R
import io.redandroid.gameofthrones.common.DataBindingAdapter
import io.redandroid.gameofthrones.common.DataBindingViewHolder
import io.redandroid.gameofthrones.common.ItemClickListener


/**
 * This [RecyclerView.Adapter] is used to display a list of [House]s in an RecyclerView.
 */
class HousesAdapter(private val clickListener: ItemClickListener) : DataBindingAdapter<House>(DiffCallback) {

    override fun getItemViewType(position: Int) = R.layout.item_house

    override fun onBindViewHolder(holder: DataBindingViewHolder<House>, position: Int) {
        val item = getItem(position)
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
