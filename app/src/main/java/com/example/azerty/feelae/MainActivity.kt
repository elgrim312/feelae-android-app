package com.example.azerty.feelae

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import com.example.azerty.feelae.fragments.FirstFragment
import com.example.azerty.feelae.fragments.HomeFragment
import com.example.azerty.feelae.fragments.PrescriptionListFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private val firstFragment: FirstFragment by lazy {
        FirstFragment.newInstance()
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
        openFragment(homeFragment)
/*        val fragmentAdapter = MenuPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
        tabs_main.getTabAt(0)!!.setIcon(R.drawable.ic_person_24px)
        tabs_main.getTabAt(1)!!.setIcon(R.drawable.ic_phone_24px)
        tabs_main.getTabAt(2)!!.setIcon(R.drawable.ic_content_copy_24px)*/
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.call-> {
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                openFragment(firstFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.prescription -> {
                Log.d("debug", "prescription")
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
