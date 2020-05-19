package com.example.petproject.ui.items.adapter

import android.view.View
import android.widget.TextView
import com.example.petproject.R
import com.example.petproject.model.TestObject
import com.example.petproject.ui.base.BaseViewHolder

class ItemVH(itemView: View, viewIds: Set<Int?>) : BaseViewHolder<TestObject>(itemView, viewIds) {

    private lateinit var label: TextView
    private lateinit var value: TextView

    override fun bindView(itemView: View) {
        label = itemView.findViewById(R.id.tvLabel)
        value = itemView.findViewById(R.id.tvValue)
    }

    override fun bindData(item: TestObject) {
        super.bindData(item)
        label.text = item.label
        value.text = item.value
    }
}