package com.example.petproject.ui.base

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.petproject.util.Constants
import com.example.petproject.util.UtilUi
import com.google.android.material.snackbar.Snackbar


open class BaseActivity: AppCompatActivity(), BaseView {

    private var TAG: String? = null

    init {
        val className = this.javaClass.simpleName
        TAG = className + Constants.TAG_SUFFIX
    }

    override fun showMessage(resId: Int) {
        val msg = getString(resId)
        showMessage(msg)
    }

    override fun showMessage(msg: String?) {
        UtilUi.snack (
            findViewById (android.R.id.content),
            msg,
            Snackbar.LENGTH_LONG);
    }

    override fun showMessage(msg: String?,
                             actionName: Int,
                             runnable: Unit?) {
        UtilUi.snack (
            findViewById (android.R.id.content),
            msg,
            Snackbar.LENGTH_LONG,
            actionName,
            { runnable });
    }

    override fun showMessage(resId: Int, actionName: Int, runnable: Unit?) {
        val msg = getString(resId)
        showMessage(msg, actionName, runnable)
    }

    override fun hideKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

}