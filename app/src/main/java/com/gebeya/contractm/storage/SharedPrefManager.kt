package com.gebeya.contractm.storage

import android.content.Context
import android.content.SharedPreferences
import com.gebeya.contractm.R
import com.gebeya.contractm.models.User

class SharedPrefManager  (context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_NAME = "user_fname"
        const val USER_lNAME = "user_lname"
        const val IS_LOGIN = "IsLoggedIn"
        private var mInstance: SharedPrefManager? = null
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String , fname : String , lname :String ) {
        val editor = prefs.edit()
        editor.putBoolean(IS_LOGIN , true)
        editor.putString(USER_TOKEN, token)
        editor.putString(USER_NAME, fname)
        editor.putString(USER_lNAME , lname)

        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): HashMap<String, String?>? {

        val user = HashMap<String, String?>()
        user[USER_TOKEN] = prefs.getString(USER_TOKEN, null)
        user[USER_NAME] = prefs.getString(USER_NAME, null)
        user[USER_lNAME] = prefs.getString(USER_lNAME, null)
        return user


        //return prefs.getString(USER_TOKEN, null)
    }
        fun clear() {
       // val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
        fun isLoggedIn(): Boolean{
       // get() {

            //val sharedPreferences =
              //  mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return prefs.getBoolean(IS_LOGIN , false)
        }

    @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }





//    val isLoggedIn: Boolean
//        get() {
//            val sharedPreferences =
//                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
//            return sharedPreferences.getInt("id", -1) != -1
//        }
//
//    val user: User
//        get() {
//            val sharedPreferences =
//                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
//            return User(
//                sharedPreferences.getInt("id", -1),
//                sharedPreferences.getString("firstName", null),
//                sharedPreferences.getString("lastName", null),
//                sharedPreferences.getString("email", null)
//            )
//        }
//
//
//    fun saveUser(user: User) {
//
//        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//
//        editor.putInt("id", user.id)
//        editor.putString("firstName", user.firstName)
//        editor.putString("lastName", user.lastName)
//        editor.putString("email", user.email)
//
//        editor.apply()
//
//    }
//
//    fun clear() {
//        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.clear()
//        editor.apply()
//    }
//
//    companion object {
//        private val SHARED_PREF_NAME = "my_shared_preff"
//        private var mInstance: SharedPrefManager? = null
//
//        @Synchronized
//        fun getInstance(mCtx: Context): SharedPrefManager {
//            if (mInstance == null) {
//                mInstance = SharedPrefManager(mCtx)
//            }
//            return mInstance as SharedPrefManager
//        }
//    }
//}