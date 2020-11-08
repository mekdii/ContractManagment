package com.gebeya.contractm.activities

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
        buttonSignUpp.setOnClickListener {

            val  firstName = editTextEmaill.text.toString().trim()
            val lastName = editTextPasswordd.text.toString().trim()
            val email = editTextNamee.text.toString().trim()
            val password = editTextSchooll.text.toString().trim()


            if(email.isEmpty()){
                editTextEmaill.error = "Email required"
                editTextEmaill.requestFocus()
                return@setOnClickListener
            }


            if(password.isEmpty()){
                editTextPasswordd.error = "Password required"
                editTextPasswordd.requestFocus()
                return@setOnClickListener
            }

            if( firstName.isEmpty()){
                editTextNamee.error = "Name required"
                editTextNamee.requestFocus()
                return@setOnClickListener
            }

            if(lastName.isEmpty()){
                editTextSchooll.error = "School required"
                editTextSchooll.requestFocus()
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