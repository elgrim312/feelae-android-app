package com.example.azerty.feelae

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_spash_screen.*

class SpashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spash_screen)

        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.splashscreen)
        videoView.setVideoURI(uri)
        videoView.start()

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)
    }
}
