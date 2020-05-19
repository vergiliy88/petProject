package com.example.petproject.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.model.base.BaseItem

abstract class BaseAdapter<T: BaseItem, H: BaseViewHolder<T>>: RecyclerView.Adapter<H> {

    constructor(layoutId: Int) : super() {
        this.layoutId = layoutId
    }

    constructor(layoutId: Int, _listeners: Map<Int?, OnViewClickListener>) : super() {
        this.layoutId = layoutId
        this.listeners = _listeners
    }

    private var layoutId: Int = 0
    private val items: MutableList<T> = mutableListOf()
    private var listeners = mapOf<Int?, OnViewClickListener>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.bindData(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        val holder = newInstanceHolder(view, this.listeners.keys)
        holder.bindView(view)
        holder.bindListeners (this.listeners);
        return holder
    }

    abstract fun newInstanceHolder(view: View, viewIds: Set<Int?>): H

    fun refreshData(_items: List<T>) {
        items.clear()
        items.addAll(_items)
        notifyDataSetChanged()
    }

    interface OnViewClickListener {
        fun onClick(
            view: View?,
            value: Int?
        )
    }
}