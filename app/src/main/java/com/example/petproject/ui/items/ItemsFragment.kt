package com.example.petproject.ui.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petproject.R
import com.example.petproject.databinding.FragmentItemsBinding
import com.example.petproject.ui.base.BaseAdapter
import com.example.petproject.ui.base.BaseFragment
import com.example.petproject.ui.items.adapter.ItemAdapter

class ItemsFragment(): BaseFragment() {
    private var _binding: FragmentItemsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ItemAdapter

    private var model: ItemsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(ItemsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listeners = mutableMapOf<Int?, BaseAdapter.OnViewClickListener>()

        listeners.put(R.id.tvValue, object : BaseAdapter.OnViewClickListener{
            override fun onClick(view: View?, value: Int?) {
                showToast(value!!)
            }
        })

        listeners.put(R.id.tvLabel, object : BaseAdapter.OnViewClickListener{
            override fun onClick(view: View?, value: Int?) {
                showToastLabel(value!!)
            }
        })

        adapter = ItemAdapter(R.layout.default_item)
        binding.rvItems.layoutManager = LinearLayoutManager(context)
        binding.rvItems.adapter = adapter

        model?.items?.observe(viewLifecycleOwner, Observer { items ->
            adapter.refreshData(items)
        })
    }

    private fun showToast(pos: Int) {
        Toast.makeText(context, "Click on VALUE $pos", Toast.LENGTH_LONG).show()
    }

    private fun showToastLabel(pos: Int) {
        Toast.makeText(context, "Click on LABEL $pos", Toast.LENGTH_LONG).show()
    }

    companion object {
        val TAG_FRAGMENT = "items_fragment"
        fun newInstance (): ItemsFragment {
            return ItemsFragment()
        }
    }
}