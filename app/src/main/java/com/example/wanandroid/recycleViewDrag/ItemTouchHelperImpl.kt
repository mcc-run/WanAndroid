package com.example.jetpackstudy.recycleViewDrag

import androidx.recyclerview.widget.ItemTouchHelper

/*
* callback负责处理各种操作
* ItemTouchHelper负责与recycleView绑定
* */
class ItemTouchHelperImpl(private val callback: ItemTouchHelperCallback): ItemTouchHelper(callback) {

}
