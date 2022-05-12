package com.example.jetpackstudy.recycleViewDrag

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.E

class ItemTouchHelperCallback(val helperDelegate: ItemTouchDelegate): ItemTouchHelper.Callback() {
    private var EnableDrag : Boolean = false
    private var EnableSwipe : Boolean = false

    fun setEnableDrag(enable : Boolean){
        EnableDrag = enable
    }
//    是否允许拖动
    override fun isLongPressDragEnabled(): Boolean {
        return EnableDrag
    }

    fun setEnableSwipe(enable : Boolean){
        EnableSwipe = enable
    }
//    是否允许滑动
    override fun isItemViewSwipeEnabled(): Boolean {
        return EnableSwipe
    }

//    设置是否允许用户滑动或拖动
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val flags = helperDelegate.getMovementFlags(recyclerView, viewHolder)
        return if (flags != null && flags.size >= 2) {
            makeMovementFlags(flags[0], flags[1])
        } else makeMovementFlags(0, 0)
    }

//    当用户正在拖动子view时调用
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return helperDelegate.onMove(recyclerView,viewHolder.adapterPosition, target.adapterPosition)
    }

//    当用户正在滑动子view时调用
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        helperDelegate.onSwiped(viewHolder.adapterPosition, direction)
    }



    /**
     * 当呗拖动或者被滑动的viewholder改变时调用
     * ACTION_STATE_SWIPE：当view刚被滑动时返回
     * ACTION_STATE_DRAG：当view刚被拖动时返回
     * ACTION_STATE_IDLE：当view即没被拖动也没被滑动时返回
     */
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        when(actionState) {
            ACTION_STATE_SWIPE -> {
                helperDelegate.uiOnSwiping(viewHolder)
            }
            ACTION_STATE_DRAG -> {
                helperDelegate.uiOnDragging(viewHolder)
            }
        }
    }

    /**
     * 当view被拖动或被滑动动作结束后调用
     */
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        helperDelegate.uiOnClearView(recyclerView, viewHolder)
    }
}
