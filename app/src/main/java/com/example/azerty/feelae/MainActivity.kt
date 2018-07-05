package com.example.azerty.feelae

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.example.azerty.feelae.fragments.ProfilFragment
import com.example.azerty.feelae.fragments.HomeFragment
import com.example.azerty.feelae.fragments.PrescriptionListFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private val profilFragment: ProfilFragment by lazy {
        ProfilFragment.newInstance()
    }

    private val homeFragment: HomeFragment by lazy {
        HomeFragment.newInstance()
    }

    private val prescriptionListFragment: PrescriptionListFragment by lazy {
        PrescriptionListFragment.newInstance()
    }

    private val mNotificationTime = Calendar.getInstance().timeInMillis + 5000 //Set after 5 seconds from the current time.
    private var mNotified = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabs_main.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        tabs_main.setSelectedItemId(R.id.call);
        openFragment(homeFragment)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.call-> {
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                openFragment(profilFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.prescription -> {
                openFragment(prescriptionListFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.viewpager_main, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
