package com.example.azerty.feelae.fragments


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.azerty.feelae.R
import com.example.azerty.feelae.RecapActivity
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment: Fragment() {

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        consultation_button.setOnClickListener({
            val intent = Intent(this.context,RecapActivity::class.java)
            startActivity(intent)
        })
    }
}