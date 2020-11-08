package com.gebeya.contractm.activities.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gebeya.contractm.R
import com.gebeya.contractm.api.RetrofitClient
import kotlinx.android.synthetic.main.activity_form_contract.*
import kotlinx.android.synthetic.main.activity_update_form.*
import kotlinx.android.synthetic.main.activity_update_form.lemail
import kotlinx.android.synthetic.main.activity_update_form.lfname
import kotlinx.android.synthetic.main.activity_update_form.llast
import kotlinx.android.synthetic.main.activity_update_form.lphone
import kotlinx.android.synthetic.main.activity_update_form.submit
import kotlinx.android.synthetic.main.activity_update_form.temail
import kotlinx.android.synthetic.main.activity_update_form.tfirst
import kotlinx.android.synthetic.main.activity_update_form.tlast
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class UpdateForm : AppCompatActivity() {
    private lateinit var apiClient: RetrofitClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_form)

        apiClient = RetrofitClient





            submit.setOnClickListener {
                val id  = intent.getStringExtra("id")
                val lFirstName = lfname.text.toString().trim()
                val lLastName = llast.text.toString().trim()
                val lemail = lemail.text.toString().trim()
                val lPhone = lphone.text.toString().trim()
                val tFirstName = tfirst.text.toString().trim()
                val tLastName = tlast.text.toString().trim()
                val temail = temail.text.toString().trim()
                //val tPhone = tphone.text.toString().trim()
                val map = HashMap<String, String>()
                map["lFirstName"] = lFirstName
                map["lLastName"] = lLastName
                map["lemail"] = lemail
                map["tFirstName"] = tFirstName
                map["tLastName"] = tLastName
                map["temail"] = temail



                if (id != null) {
                    RetrofitClient.getApiService(this).updateForm(id, map)
                        .enqueue(object : retrofit2.Callback<String> {
                            override fun onFailure(call: Call<String>, t: Throwable) {
                                Toast.makeText(applicationContext, id, Toast.LENGTH_LONG).show()

                                // Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                            }

                            override fun onResponse(
                                call: Call<String>,
                                response: Response<String>
                            ) {
                                val loginResponse = response.body()

                                if (response.isSuccessful) {
                                    Toast.makeText(applicationContext, "updated", Toast.LENGTH_LONG)
                                        .show()


                                } else {
                                    Toast.makeText(
                                        applicationContext,
                                        "ayseram on response",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    // Error logging in
                                }

                            }
                        })
                }

            }


        }}