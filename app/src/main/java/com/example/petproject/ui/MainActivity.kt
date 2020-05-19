package com.example.petproject.ui

import android.os.Bundle
import android.view.View
import com.example.petproject.R
import com.example.petproject.ui.base.BaseActivity
import com.example.petproject.ui.home.HomeFragment
import com.example.petproject.ui.items.ItemsFragment
import com.example.petproject.util.UtilUi
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView =
            findViewById<View>(R.id.bottom_navigation) as BottomNavigationView

        val fragments = supportFragmentManager.fragments
        if (fragments.size == 0) {
            UtilUi.showFragment(supportFragmentManager, HomeFragment.newInstance(), HomeFragment.TAG_FRAGMENT)
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_page -> {
                    UtilUi.showFragment(supportFragmentManager, HomeFragment.newInstance(),  HomeFragment.TAG_FRAGMENT)
                }
                R.id.item_page -> {
                    UtilUi.showFragment(supportFragmentManager, ItemsFragment.newInstance(), ItemsFragment.TAG_FRAGMENT)
                }
            }
            true
        }
    }
}
