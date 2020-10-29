package com.gebeya.contractm.framework.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gebeya.contractm.framework.util.logD

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        d("onCreate")



    }

    override fun onStart() {
        super.onStart()
        d("onStart")
    }

    override fun onResume() {
        super.onResume()
        d("onResume")

    }

    override fun onPause() {
        super.onPause()
        d("onPause")
    }

    override fun onStop() {
        super.onStop()
        d( "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        d( "onDestroy")
    }
    protected fun d(message: String) {
        val tag = this::class.simpleName
        logD(this, message)

    }


}