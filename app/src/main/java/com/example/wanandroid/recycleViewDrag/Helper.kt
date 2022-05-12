package com.example.wanandroid.recycleViewDrag

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackstudy.recycleViewDrag.ItemTouchDelegate
import com.example.jetpackstudy.recycleViewDrag.ItemTouchHelperCallback
import com.example.jetpackstudy.recycleViewDrag.ItemTouchHelperImpl

object Helper {

    private fun getCallback() =
        ItemTouchHelperCallback(object : ItemTouchDelegate {
            override fun onMove(
                recyclerView: RecyclerView,
                srcPosition: Int,
                targetPosition: Int
            ): Boolean {
                recyclerView.adapter?.notifyItemMoved(srcPosition,targetPosition)
                return true
            }

            override fun uiOnDragging(viewHolder: RecyclerView.ViewHolder?) {
                viewHolder?.itemView?.setBackgroundColor(Color.parseColor("#22000000"))
            }

            override fun uiOnClearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                viewHolder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        })

    fun getHelper(enableDrag : Boolean, enableSwipe: Boolean): ItemTouchHelperImpl {
        val callback = getCallback()
        callback.setEnableDrag(enableDrag)
        callback.setEnableSwipe(enableSwipe)
        return ItemTouchHelperImpl(callback)
    }

}