package com.example.petproject.ui.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.petproject.R
import com.example.petproject.databinding.FragmentHomeBinding
import com.example.petproject.ui.base.BaseFragment
import com.example.petproject.util.UtilUi
import com.google.android.material.snackbar.Snackbar


class HomeFragment(): BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var model: HomeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnDetail.setOnClickListener {
            model?.updateText("42344")
            UtilUi.snack(activity!!.findViewById(android.R.id.content) , "Show Toast", Snackbar.LENGTH_LONG, R.string.yes){
                Toast.makeText(context!!, "Show Toast", Toast.LENGTH_SHORT).show()
            }
        }
        model?.text?.observe(viewLifecycleOwner, Observer<String> { item ->
            binding.textView.text = item
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    companion object {
        val TAG_FRAGMENT = "home_fragment"
        fun newInstance (): HomeFragment {
            return HomeFragment()
        }
    }
}