package com.example.jetpackstudy.recycleViewDrag

import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

interface ItemTouchDelegate {

    fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Array<Int> {
        val layoutManager = recyclerView.layoutManager
        var swipeFlag = 0
        var dragFlag = 0
        if (layoutManager is LinearLayoutManager) {
            if (layoutManager.orientation == LinearLayoutManager.VERTICAL) {
                swipeFlag = 0   // 不允许滑动
                dragFlag = (UP or DOWN)     // 允许上下拖拽
            } else {
                swipeFlag = 0
                dragFlag = (LEFT or RIGHT)  // 允许左右滑动
            }
        }

        return arrayOf(dragFlag, swipeFlag)
    }

    fun onMove(recyclerView: RecyclerView,srcPosition: Int, targetPosition:Int): Boolean = true

    fun onSwiped(position: Int, direction: Int) {}

    // 刚开始滑动时，需要进行的UI操作
    fun uiOnSwiping(viewHolder: RecyclerView.ViewHolder?) {}

    // 刚开始拖动时，需要进行的UI操作
    fun uiOnDragging(viewHolder: RecyclerView.ViewHolder?) {}

    // 用户释放与当前itemView的交互时，可在此方法进行UI的复原
    fun uiOnClearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {}
}
