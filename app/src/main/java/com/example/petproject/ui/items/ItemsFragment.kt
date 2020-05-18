package com.example.petproject.ui.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petproject.databinding.FragmentItemsBinding
import com.example.petproject.ui.base.BaseFragment

class ItemsFragment(): BaseFragment() {
    private var _binding: FragmentItemsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root
    }
    companion object {
        val TAG_FRAGMENT = "items_fragment"
        fun newInstance (): ItemsFragment {
            return ItemsFragment()
        }
    }
}