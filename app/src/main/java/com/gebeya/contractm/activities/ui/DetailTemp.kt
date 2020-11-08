package com.gebeya.contractm.activities.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gebeya.contractm.R
import com.gebeya.contractm.activities.ui.slideshow.SlideshowFragment
import kotlinx.android.synthetic.main.activity_detail_temp.*

class DetailTemp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail_temp)
        usetemplate.setOnClickListener {

            val intent = Intent(this ,FormContract::class.java)
            startActivity(intent)
        }

        setSupportActionBar(toolbarr)
       toolbarr.setNavigationOnClickListener {
          Toast.makeText(applicationContext , "back icon clicked" , Toast.LENGTH_SHORT).show()
           val fragmentManager = supportFragmentManager
           val fragmentTransaction = fragmentManager.beginTransaction()
           fragmentTransaction.replace(R.id.nav_host_fragment, SlideshowFragment()).commit()


        }
    }
}