package com.gebeya.contractm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.widget.Toast
import com.gebeya.contractm.R
import com.gebeya.contractm.api.RetrofitClient
import com.gebeya.contractm.models.LoginResponse
import com.gebeya.contractm.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*


import kotlinx.android.synthetic.main.activity_registor.*

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


        apiClient = RetrofitClient
        sessionManager = SharedPrefManager(this)
        //code for showing and hiding password
        ivShowHidePass.setOnClickListener {
            mIsShowPass = !mIsShowPass
            if ( mIsShowPass) {
                // To show the password
                editTextPasswor_login.transformationMethod = HideReturnsTransformationMethod.getInstance()
                ivShowHidePass.setImageResource(R.drawable.hide)
            } else {
                // To hide the password
                editTextPasswor_login.transformationMethod = PasswordTransformationMethod.getInstance()
                ivShowHidePass.setImageResource(R.drawable.show)
            }
            // This line of code to put the pointer at the end of the password string
            editTextPasswor_login.setSelection(editTextPasswor_login.text.toString().length)
            //showPassword(mIsShowPass)
        }
        //  showPassword(mIsShowPass)


        signup.setOnClickListener {

           val intent = Intent(this , com.gebeya.contractm.activities.Registor::class.java)
            startActivity(intent)
        }
        buttonLogin.setOnClickListener {

            val email = editTextEmail_login.text.toString().trim()
            val password = editTextPasswor_login.text.toString().trim()

            if (email.isEmpty()) {
                editTextEmail.error = "Email required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                editTextEmail.error = "please input valid email"
                editTextEmail.requestFocus()
                return@setOnClickListener

            }



            if (password.isEmpty()) {
                editTextPasswor_login.error = "Password required"
                editTextPasswor_login.requestFocus()
                return@setOnClickListener
            }
//            else if(!PASSWORD_PATTERN.matcher(password).matches()){
//                editTextPasswor.error = "password too weak"
//                editTextPasswor.requestFocus()
//                return@setOnClickListener
//
//            }

            val map = HashMap<String, String>()

            map["email"] = email
            map["password"] = password


                RetrofitClient.getApiService(this).loginUser(map)
                    .enqueue(object: Callback<LoginResponse> {
                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            val loginResponse = response.body()

                            if (loginResponse?.success == true) {
                                if (loginResponse != null) {

                                    sessionManager.saveAuthToken(loginResponse.token , loginResponse.firstName , loginResponse.lastName, loginResponse.email)
                                    Toast.makeText(
                                        applicationContext,
                                        "successful",
                                        Toast.LENGTH_LONG
                                    ).show()
                                       intent = Intent (applicationContext, Navigation::class.java)
                                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        startActivity(intent)
                                    }


                                }

                            else {
                                Toast.makeText(
                                    applicationContext,
                                    "something went wrong",
                                    Toast.LENGTH_LONG
                                ).show()
                                // Error logging in
                            }
                        }
                    })}}




    override fun onStart() {
        super.onStart()
        if (sessionManager.isLoggedIn()){

                val intent = Intent (this, Navigation::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

        }

    }

