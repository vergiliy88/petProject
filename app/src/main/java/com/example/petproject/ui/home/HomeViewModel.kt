package com.example.petproject.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.petproject.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel: BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun updateText(text: String) {
        _text.value = text
        viewModelScope.launch { intervalUpdate() }
    }

    private suspend fun intervalUpdate() {
        var increment = 1
        for (i in 1..10) {
            delay(1000)
            increment++
            _text.value = "$increment"
        }
    }
}