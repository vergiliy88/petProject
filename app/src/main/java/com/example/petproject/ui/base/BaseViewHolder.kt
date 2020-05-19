package com.example.petproject.ui.base

import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.model.base.BaseItem
import com.example.petproject.ui.base.BaseAdapter.OnViewClickListener


abstract class BaseViewHolder<I: BaseItem>(itemView: View, viewIds: Set<Int?>) : RecyclerView.ViewHolder(itemView) {

    private var clickedViews: SparseArray<View>? = null

    open fun onBindView(itemView: View) {}
    open fun onBindData(item: I) {}

    init {
        clickedViews = SparseArray(viewIds.size)
        for (viewId in viewIds) {
            val clickedView = viewId?.let { itemView.findViewById<View>(it) }
            if (clickedView != null) {
                clickedView.isClickable = true
                clickedViews!!.append(
                    viewId,
                    clickedView
                )
            }
        }
    }

    open fun bindListeners(listeners: Map<Int?, OnViewClickListener>) {
        for ((key, value) in listeners) {
            if (key != null) {
                clickedViews?.get(
                    key
                )?.setOnClickListener { v ->
                    value
                        .onClick(v, getOnClickValue())
                }
            }
        }
    }

    open fun getOnClickValue(): Int? {
        return adapterPosition
    }

    open fun unbindListeners() {
        for (i in 0 until clickedViews?.size()!!) {
            clickedViews?.valueAt(i)?.setOnClickListener(null)
        }
    }
}