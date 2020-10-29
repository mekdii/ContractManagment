package com.gebeya.contractm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gebeya.contractm.R
import com.gebeya.contractm.api.Api
import com.gebeya.contractm.api.RetrofitClient
import com.gebeya.contractm.models.DefaultResponse
import kotlinx.android.synthetic.main.activity_registor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class Registor : AppCompatActivity() {
    // private var retrofit: Retrofit? = RetrofitClient.getInstance()
    private var retrofitInterface: Api? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registor)
//        textViewLogin.setOnClickListener {
//            val intent = Intent(this, Login::class.java)
//            startActivity(intent)
//        }
        buttonSignUp.setOnClickListener {

            val  firstName = editTextEmail.text.toString().trim()
            val lastName = editTextPassword.text.toString().trim()
            val email = editTextName.text.toString().trim()
            val password = editTextSchool.text.toString().trim()


            if(email.isEmpty()){
                editTextEmail.error = "Email required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }


            if(password.isEmpty()){
                editTextPassword.error = "Password required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }

            if( firstName.isEmpty()){
                editTextName.error = "Name required"
                editTextName.requestFocus()
                return@setOnClickListener
            }

            if(lastName.isEmpty()){
                editTextSchool.error = "School required"
                editTextSchool.requestFocus()
                return@setOnClickListener
            }

            val map = HashMap<String, String>()
            map["firstName"] =firstName
            map["lastName"]=lastName
            map["email"] = email
            map["password"] = password



            RetrofitClient.getApiService(this).createUser(map)
                .enqueue(object: Callback<DefaultResponse> {
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                      Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                   }

                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                    }

                })

        }
    }
}