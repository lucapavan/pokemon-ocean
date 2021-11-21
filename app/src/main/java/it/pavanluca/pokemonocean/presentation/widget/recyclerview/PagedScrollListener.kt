package it.pavanluca.pokemonocean.presentation.widget.recyclerview

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Luca Pavan on 21/11/21
 */
class PagedScrollListener(
    private val pageListener: PageListener
) : RecyclerView.OnScrollListener() {

    private var lastItemCount = 0 // keep track of number of items

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager
        val lastVisibleItemPosition = when (layoutManager) {
            is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
            is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
            else -> return
        }

        val currentItemCount = layoutManager.itemCount
        if (lastItemCount != currentItemCount && currentItemCount - lastVisibleItemPosition < 2) {
            lastItemCount = currentItemCount
            pageListener.onEndReached()
        }
    }
}