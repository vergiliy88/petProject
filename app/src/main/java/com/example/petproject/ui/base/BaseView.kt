package com.example.petproject.ui.base

import androidx.annotation.StringRes


interface BaseView {
    fun showMessage(@StringRes resId: Int)
    fun showMessage(msg: String?)

    fun showMessage(
        msg: String?,
        @StringRes actionName: Int,
        runnable: Runnable?
    )

    fun showMessage(
        @StringRes resId: Int,
        @StringRes actionName: Int,
        runnable: Runnable?
    )

    fun hideKeyboard()

}