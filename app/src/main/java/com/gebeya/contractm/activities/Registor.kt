package com.gebeya.contractm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.widget.Toast
import com.gebeya.contractm.R
import com.gebeya.contractm.api.Api
import com.gebeya.contractm.api.RetrofitClient
import com.gebeya.contractm.framework.util.PASSWORD_PATTERN
import com.gebeya.contractm.models.DefaultResponse
import com.gebeya.contractm.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_registor.*


import kotlinx.android.synthetic.main.fragment_third_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registor : AppCompatActivity() {
    var mIsShowPass = false
    private lateinit var sessionManager: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registor)

        setSupportActionBar(tool)
        tool.setNavigationOnClickListener {
           // Toast.makeText(this, "yaaay" , Toast.LENGTH_SHORT).show()
            val intent = Intent(this , Login::class.java)
            startActivity(intent)
//            val ft = fragmentManager!!.beginTransaction()
//            ft.replace(R.id.fragment, fragment, "NewFragmentTag")
//            ft.commit()
        }

        //code for showing and hiding password
        ivShowHidePas.setOnClickListener {
            mIsShowPass = !mIsShowPass
            if ( mIsShowPass) {
                // To show the password
                editTextPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                ivShowHidePas.setImageResource(R.drawable.hide)
            } else {
                // To hide the password
                editTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                ivShowHidePas.setImageResource(R.drawable.show)
            }
            // This line of code to put the pointer at the end of the password string
            editTextPassword.setSelection(editTextPassword.text.toString().length)
            //showPassword(mIsShowPass)
        }

        //code for showing and hiding confirmPassword
        ivShowHidecon.setOnClickListener {
            mIsShowPass = !mIsShowPass
            if ( mIsShowPass) {
                // To show the password
                confirmp.transformationMethod = HideReturnsTransformationMethod.getInstance()
                ivShowHidecon.setImageResource(R.drawable.hide)
            } else {
                // To hide the password
                confirmp.transformationMethod = PasswordTransformationMethod.getInstance()
                ivShowHidecon.setImageResource(R.drawable.show)
            }
            // This line of code to put the pointer at the end of the password string
            confirmp.setSelection(confirmp.text.toString().length)
            //showPassword(mIsShowPass)
        }


        sessionManager = SharedPrefManager(this)

        buttonSignUp.setOnClickListener {
            val  email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val firstName = editTextfirst.text.toString().trim()
            val lastName = editTextlast.text.toString().trim()
            val confirm = confirmp.text.toString().trim()
            if(email.isEmpty()){
                editTextEmail.error = "Email required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                editTextEmail.error = "please input valid email"
                editTextEmail.requestFocus()
                return@setOnClickListener

            }


            if(password.isEmpty()){
                editTextPassword.error = "Password required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }
            else if(!PASSWORD_PATTERN.matcher(password).matches()){
                editTextPassword.error = "password too weak"
                editTextPassword.requestFocus()
                return@setOnClickListener

            }

            if(confirm.isEmpty()){
                confirmp.error = "you should confirm"
                confirmp.requestFocus()
                return@setOnClickListener
            }
            if(password != confirm){
                confirmp.error = "password not match"
                confirmp.requestFocus()
                return@setOnClickListener

            }

//            else if(!PASSWORD_PATTERN.matcher(password).matches()){
//                editTextPassword.error = "password too weak"
//                editTextPassword.requestFocus()
//                return@setOnClickListener
//
//            }

            if( firstName.isEmpty()){
                editTextfirst.error = "Name required"
                editTextfirst.requestFocus()
                return@setOnClickListener
            }

            if(lastName.isEmpty()){
                editTextlast.error = "School required"
                editTextlast.requestFocus()
                return@setOnClickListener
            }


            val map = HashMap<String, String>()
            map["email"] = email
            map["password"] = password
            map["firstName"] =firstName
            map["lastName"]=lastName




                RetrofitClient.getApiService(this).createUser(map)
                    .enqueue(object: Callback<DefaultResponse> {
                        override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                            Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                            //findNavController().navigate(R.id.action_registor_to_nav_slideshow)
                            sessionManager.saveAuthToken(password , firstName , lastName , email)

                                val intent = Intent (this@Registor, Navigation::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            }




                    })
            }




        }







    override fun onStart() {
        super.onStart()
        if (sessionManager.isLoggedIn()){
                val intent = Intent (this, Navigation::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

        }

    }


