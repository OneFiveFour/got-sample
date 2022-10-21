package io.redandroid.gameofthrones.common.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

/**
 * A decorator class that adds a vertical space on top of every element of a [RecyclerView],
 * except the first element
 *
 * @param verticalMargin is the dp dimen of the vertical margin
 */
class VerticalItemDecoration(@DimenRes private val verticalMargin: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val marginDp = view.context.resources.getDimensionPixelOffset(verticalMargin)
        with(outRect) {
            bottom = marginDp
            top = marginDp
        }
    }
}