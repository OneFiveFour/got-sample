package io.redandroid.gameofthrones.screens.houses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.l4digital.fastscroll.FastScroller
import io.redandroid.data.model.House
import io.redandroid.data.model.HouseListItem
import io.redandroid.data.model.ListHeader
import io.redandroid.gameofthrones.R
import io.redandroid.gameofthrones.common.recyclerview.DataBindingAdapter
import io.redandroid.gameofthrones.common.recyclerview.DataBindingViewHolder
import io.redandroid.gameofthrones.common.recyclerview.ItemClickListener


/**
 * The Paging adapter for [House]s.
 *
 * This adapter is using the Jetpack Paging library 3 and works together with [androidx.paging.PagingData]
 * Use [submitData] to set a new list of items. They are compared using the provided DiffCallback.
 */
class HousesAdapter(private val clickListener: ItemClickListener) : PagingDataAdapter<HouseListItem, DataBindingViewHolder<HouseListItem>>(DiffCallback), FastScroller.SectionIndexer {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<HouseListItem> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return DataBindingViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ListHeader -> R.layout.item_list_header
            else -> R.layout.item_house
        }
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<HouseListItem>, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item, clickListener)
    }

    /**
     * The DiffCallback is used by the [DataBindingAdapter] to check which items are
     * completely new and which just changed its content. When defining the Boolean checks, be
     * as precise as possible to avoid strange behaviour of the RecyclerView items.
     */
    object DiffCallback : DiffUtil.ItemCallback<HouseListItem>() {
        override fun areItemsTheSame(oldItem: HouseListItem, newItem: HouseListItem): Boolean {
            return when {
                oldItem is House && newItem is House -> oldItem.id == newItem.id
                oldItem is ListHeader && newItem is ListHeader -> oldItem.letter == newItem.letter
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: HouseListItem, newItem: HouseListItem): Boolean {
            return when {
                oldItem is House && newItem is House -> oldItem.name == newItem.name
                oldItem is ListHeader && newItem is ListHeader -> oldItem.letter == newItem.letter
                else -> false
            }
        }

    }

    /**
     * @return the alphabet letter for FastScrolling
     */
    override fun getSectionText(position: Int): CharSequence {
        if (position < 0) return ""
        return when (val item = getItem(position)) {
            is ListHeader -> item.letter
            is House -> item.name.first().toString()
            else -> ""
        }
    }
}
