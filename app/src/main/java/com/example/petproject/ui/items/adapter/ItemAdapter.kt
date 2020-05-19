package com.example.petproject.ui.items.adapter

import android.view.View
import com.example.petproject.model.TestObject
import com.example.petproject.ui.base.BaseAdapter

class ItemAdapter: BaseAdapter<TestObject, ItemVH> {

    constructor(resId: Int): super(resId)
    constructor(resId: Int, listeners: MutableMap<Int?, OnViewClickListener>): super(resId, listeners)

    override fun newInstanceHolder(view: View, viewIds: Set<Int?>): ItemVH {
        return ItemVH(view, viewIds);
    }
}