package com.example.petproject.util

import android.util.TypedValue.COMPLEX_UNIT_PX
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.petproject.R
import com.google.android.material.snackbar.Snackbar


class UtilUi {
    companion object{
        fun snack(
            rootView: View?,
            msg: String?,
            duration: Int
        ) {
            snack(
                rootView,
                msg,
                duration,
                0,
                null
            )
        }

        fun snack(
            rootView: View?,
            msg: String?,
            duration: Int,
            @StringRes action: Int,
            runnable: Runnable?
        ) {
            if (rootView == null) return
            val snackbar: Snackbar = Snackbar.make(
                rootView,
                msg.toString(),
                duration
            )
            val snackView: View = snackbar.view

            // изменена ширина snackbar на планшетах, теперь она на всю ширину экрана
            snackView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            val tvMessage: TextView = snackView.findViewById(
                com.google.android.material.R.id.snackbar_text
            )

            // увеличен размер текста сообщения
            setTextSize(tvMessage, R.dimen.snack_text_size)
            tvMessage.isSingleLine = false
            tvMessage.textAlignment = View.TEXT_ALIGNMENT_CENTER
            val context = snackView.context
            snackView.setBackgroundColor(ContextCompat.getColor(context, R.color.snackColor));

            if (runnable != null) {
                snackbar.setAction(
                    action,
                    { v -> runnable.run() })
                val tvAction: TextView = snackView.findViewById(
                    com.google.android.material.R.id.snackbar_action
                )
                setTextSize(tvAction, R.dimen.snack_text_size)
                tvAction.isAllCaps = true
            }
            snackbar.show()
        }

        fun setTextSize(
            tv: TextView?,
            @DimenRes textSize: Int
        ) {
            tv?.setTextSize(
                COMPLEX_UNIT_PX,
                tv.context.resources
                    .getDimension(textSize)
            )
        }

        fun replaceFragment(
            fm: FragmentManager?,
            fragment: Fragment?
        ) {
            replaceFragment(fm, fragment, false, null)
        }

        fun replaceFragment(
            fm: FragmentManager?,
            fragment: Fragment?,
            backStackTag: String?
        ) {
            replaceFragment(fm, fragment, true, backStackTag)
        }

        private fun replaceFragment(
            fm: FragmentManager?,
            fragment: Fragment?,
            backStack: Boolean,
            backStackTag: String?
        ) {
            if (fm == null ||
                fragment == null
            ) {
                return
            }
            val transaction: FragmentTransaction = fm.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right
                )
                .replace(R.id.container_main, fragment, backStackTag)
            if (backStack) transaction.addToBackStack(backStackTag)
            transaction.commit()
        }

        fun showFragment(fm: FragmentManager?, fragment: Fragment?, tag: String) {
            if (fm == null ||
                fragment == null
            ) {
                return
            }
            val fragments = fm.fragments
            var targetFragment: Fragment? = null
            val transaction = fm.beginTransaction()
            for (itemFragment in fragments) {
                if (itemFragment.tag == tag) {
                    targetFragment = itemFragment
                }
               transaction.hide(itemFragment)
            }
            if (targetFragment == null) {
               transaction.add(R.id.container_main, fragment, tag).show(fragment)
            } else {
               transaction.show(targetFragment)
            }
            transaction.commit()
        }
    }
}