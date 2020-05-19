package com.example.petproject.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.petproject.model.TestObject
import com.example.petproject.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ItemsViewModel: BaseViewModel() {
    private val _items = MutableLiveData<List<TestObject>>().apply {
        value = mutableListOf()
    }
    val items: LiveData<List<TestObject>> = _items

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    private suspend fun loadData() {
        delay(3000)
        val tempList: MutableList<TestObject> = mutableListOf()

       tempList.add(0, TestObject("label1", "value1"))
       tempList.add(1, TestObject("label2", "value2"))
       tempList.add(2, TestObject("label3", "value3"))
       tempList.add(3, TestObject("label4", "value4"))
       tempList.add(4, TestObject("label5", "value5"))

        _items.value = tempList
    }
}