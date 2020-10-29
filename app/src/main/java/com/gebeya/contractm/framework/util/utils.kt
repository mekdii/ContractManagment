package com.gebeya.contractm.framework.util

import android.util.Log

private const val TAG = "CONTRACT-TAG-DEBUG"
fun logD(source: Any, message: String) {

    val name = source::class.simpleName
    val output = "$name : $message"
    Log.d(TAG, output)
}