package com.gebeya.contractm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import com.gebeya.contractm.R
import com.gebeya.contractm.api.RetrofitClient
import com.gebeya.contractm.models.LoginResponse
import com.gebeya.contractm.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*


import kotlinx.android.synthetic.main.activity_registor.*
import kotlinx.android.synthetic.main.fragment_signin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    var mIsShowPass = false
    private lateinit var sessionManager: SharedPrefManager
    private lateinit var apiClient: RetrofitClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ivShowHidePasss.setOnClickListener {
            mIsShowPass = !mIsShowPass
            showPassword(mIsShowPass)
        }

        showPassword(mIsShowPass)

        apiClient = RetrofitClient
        sessionManager = SharedPrefManager(this)

        signupp.setOnClickListener {
            val intent  = Intent(this , Registor::class.java)
            startActivity(intent)
        }


        buttonLoginn.setOnClickListener {

            val email = editTextEmail.text.toString().trim()
            val password = editTextPasswordd.text.toString().trim()

            if(email.isEmpty()){
                editTextEmail.error = "Email required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }


            if(password.isEmpty()){
                editTextPasswordd.error = "Password required"
                editTextPasswordd.requestFocus()
                return@setOnClickListener
            }

            val map = HashMap<String, String>()

            map["email"] = email
            map["password"] = password


//            RetrofitClient.getApiService(this).loginUser(map)
//                .enqueue(object: Callback<LoginResponse> {
//                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//                    }
//
//                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                        val loginResponse = response.body()
//
//                        if (loginResponse?.success == true) {
//                            if (loginResponse != null) {
//
//                                sessionManager.saveAuthToken(loginResponse.token)
//
//                                val intent = Intent(this@Login, Profile::class.java)
//                                startActivity(intent)
//                            }
//
//                        } else {
//                            Toast.makeText(applicationContext, "ayseram on response", Toast.LENGTH_LONG).show()
//                            // Error logging in
//                        }
//
//                    }
//                })

        }

    }
    private fun showPassword(isShow: Boolean) {
        if (isShow) {
            // To show the password
            etPasss.transformationMethod = HideReturnsTransformationMethod.getInstance()
            ivShowHidePasss.setImageResource(R.drawable.hide)
        } else {
            // To hide the password
            etPasss.transformationMethod = PasswordTransformationMethod.getInstance()
            ivShowHidePasss.setImageResource(R.drawable.show)
        }
        // This line of code to put the pointer at the end of the password string
        etPasss.setSelection(etPasss.text.toString().length)
    }

//    override fun onStart() {
//        super.onStart()
//
//        if(SharedPrefManager.getInstance(this).isLoggedIn){
//            val intent = Intent(applicationContext, Profile::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//
//            startActivity(intent)
//        }
//    }
    }
