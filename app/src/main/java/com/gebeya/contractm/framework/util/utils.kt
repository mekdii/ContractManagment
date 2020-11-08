package com.gebeya.contractm.framework.util

import android.util.Log
import java.util.regex.Pattern

private const val TAG = "CONTRACT-TAG-DEBUG"
fun logD(source: Any, message: String) {

    val name = source::class.simpleName
    val output = "$name : $message"
    Log.d(TAG, output)
}
 val PASSWORD_PATTERN: Pattern = Pattern.compile(
    "^" +  //"(?=.*[0-9])" +         //at least 1 digit
            //"(?=.*[a-z])" +         //at least 1 lower case letter
            //"(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +  //any letter
            "(?=.*[@#$%^&+=])" +  //at least 1 special character
            "(?=\\S+$)" +  //no white spaces
            ".{4,}" +  //at least 4 characters
            "$"
)