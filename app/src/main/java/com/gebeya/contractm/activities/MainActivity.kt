package com.gebeya.contractm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gebeya.contractm.R
import com.gebeya.contractm.storage.SharedPrefManager

class MainActivity : AppCompatActivity() {
    private lateinit var sessionManager: SharedPrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SharedPrefManager(this)


    }

    override fun onStart() {
        super.onStart()
        if (sessionManager.isLoggedIn()){

                val intent = Intent (this, Navigation::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

        else{
            Log.d("fuck" ," you bitch")
        }

        }
    }
