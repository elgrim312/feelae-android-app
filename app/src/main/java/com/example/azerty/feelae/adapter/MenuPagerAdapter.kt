package com.example.azerty.feelae.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.azerty.feelae.fragments.FirstFragment
import com.example.azerty.feelae.fragments.PrescriptionListFragment
import com.example.azerty.feelae.fragments.SecondFragment

class MenuPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Liste"
            1 -> "Détail"
            else -> null
        }
    }
}