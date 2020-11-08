package com.gebeya.contractm.activities.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gebeya.contractm.R
import com.gebeya.contractm.api.RetrofitClient
import com.gebeya.contractm.models.Contract
import com.gebeya.contractm.models.DefaultResponse
import kotlinx.android.synthetic.main.activity_form_contract.*
import kotlinx.android.synthetic.main.activity_form_contract.start_date

import kotlinx.android.synthetic.main.table_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class FormContract : AppCompatActivity() {
    private lateinit var apiClient: RetrofitClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_contract)
       update_button.visibility = GONE
        val showButton = intent.getBooleanExtra("SOME_FLAG" , false)
        if (showButton) {
            print.visibility = GONE
            submit_button.visibility = GONE
            update_button.visibility = VISIBLE

        }
            apiClient = RetrofitClient
          //  setContentView(R.layout.activity_form_contract)
            val id = intent.getStringExtra("id")
            lfname.setText(intent.getStringExtra("lFname"))
            llast.setText(intent.getStringExtra("lLname"))
            lemail.setText(intent.getStringExtra("lemail"))
            lphone.setText(intent.getStringExtra("lphone"))
            laddress.setText(intent.getStringExtra("address"))
            tfirst.setText(intent.getStringExtra("tFname"))
            tlast.setText(intent.getStringExtra("tLname"))
            temail.setText(intent.getStringExtra("temail"))
            tphone.setText(intent.getStringExtra("tphone"))
            citiy.setText(intent.getStringExtra("city"))
            country.setText(intent.getStringExtra("country"))
            state.setText(intent.getStringExtra("state"))
            postalcode.setText(intent.getStringExtra("postal"))
            rental_amount.setText(intent.getStringExtra("rentamount"))
            security_deposit.setText(intent.getStringExtra("securitiyDeposit"))
            late_charges.setText(intent.getStringExtra("latecharge"))
            collector.setText(intent.getStringExtra("collector"))
            terms.setText(intent.getStringExtra("terms"))
            lsignature.setText(intent.getStringExtra("lsgnature"))
            tsignature.setText(intent.getStringExtra("tsignature"))

     //   }




        setSupportActionBar(toolbarf)


        toolbarf.setNavigationOnClickListener {
            val intent = Intent(this, DetailTemp::class.java)
            startActivity(intent)


        }

        update_button.setOnClickListener {
//               val lFirstName = lfname.text.toString().trim()
//                val lLastName = llast.text.toString().trim()
//                val lemail = lemail.text.toString().trim()
//                val lPhone = lphone.text.toString().trim()
//                val tFirstName = tfirst.text.toString().trim()
//                val tLastName = tlast.text.toString().trim()
//                val temail = temail.text.toString().trim()
//                //val tPhone = tphone.text.toString().trim()
//                val map = HashMap<String, String>()
//                map["lFirstName"] = lFirstName
//                map["lLastName"] = lLastName
//                map["lemail"] =lemail
//                map["tFirstName"]=tFirstName
//                map["tLastName"] = tLastName
//                map["temail"] = temail
            val day: Int = start_date.dayOfMonth
            val month: Int = start_date.month
            val year: Int = start_date.year
            val calendar: Calendar = Calendar.getInstance()
            calendar.set(year, month, day)

            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val formatedDate: String = sdf.format(calendar.time)
            //date for getting end date

            val day_end: Int = end_date.dayOfMonth
            val month_end: Int = end_date.month
            val year_end: Int = end_date.year
            val calendar_end: Calendar = Calendar.getInstance()
            calendar_end.set(year, month, day)

            val sdf_end = SimpleDateFormat("dd-MM-yyyy")
            val formatedDate_end: String = sdf_end.format(calendar.time)

            val lFirstName = lfname.text.toString().trim()
            val lLastName = llast.text.toString().trim()
            val lemail = lemail.text.toString().trim()
            val lPhone = lphone.text.toString().trim()
            val tFirstName = tfirst.text.toString().trim()
            val tLastName = tlast.text.toString().trim()
            val temail = temail.text.toString().trim()
            val tPhone = tphone.text.toString().trim()
            //val tOcuupants = tnumber_occupant.text.toString().trim()
            val country = country.text.toString().trim()
            val state = state.text.toString().trim()
            val city = citiy.text.toString().trim()
            val postal = postalcode.text.toString().trim()
            val startDate = formatedDate.trim()
            val endDate = formatedDate_end.trim()
            val payPeriod = pay_period.selectedItem.toString().trim()
            val rentAmount = rental_amount.text.toString().trim()
            val securityDeposit = security_deposit.text.toString().trim()
            val lateCharge = late_charges.text.toString().trim()
            val paymentMethod = payment_method.selectedItem.toString().trim()
            val collector = collector.text.toString().trim()
            //val securityDeposit = confirmp.text.toString().trim()

            val map = HashMap<String, String>()
            map["lFirstName"] = lFirstName
            map["lLastName"] = lLastName
            map["lemail"] =lemail
            map["tFirstName"]=tFirstName
            map["tLastName"] = tLastName
            map["temail"] = temail
            map["tPhone"] =tPhone
            //   map["tOcuupants"]=tOcuupants
            map["country"] = country
            map["state"] = state
            map["city"] =city
            map["postal"]=postal
            map["startDate"]=startDate
            map["endDate"] = endDate
            map["payPeriod"] = payPeriod
            map["rentAmount"] =rentAmount
            map["securityDeposit"]=securityDeposit
            map["lateCharge"] = lateCharge
            map["paymentMethod"] = paymentMethod
            map["collector"] =collector



            if (id != null) {
                RetrofitClient.getApiService(this).updateForm(id , map)
                    .enqueue(object: Callback<String> {
                        override fun onFailure(call: Call<String>, t: Throwable) {
                            Toast.makeText(applicationContext, "successfully updated", Toast.LENGTH_LONG).show()

                           // Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            val loginResponse = response.body()

                            if (response.isSuccessful) {
                                Toast.makeText(applicationContext, "updated", Toast.LENGTH_LONG).show()


                            } else {
                                Toast.makeText(applicationContext, "ayseram on response", Toast.LENGTH_LONG).show()
                                // Error logging in
                            }

                        }
                    })
            }

        }
            submit_button.setOnClickListener {
                val day: Int = start_date.dayOfMonth
                val month: Int = start_date.month
                val year: Int = start_date.year
                val calendar: Calendar = Calendar.getInstance()
                calendar.set(year, month, day)

                val sdf = SimpleDateFormat("dd-MM-yyyy")
                val formatedDate: String = sdf.format(calendar.time)
                //date for getting end date

                val day_end: Int = end_date.dayOfMonth
                val month_end: Int = end_date.month
                val year_end: Int = end_date.year
                val calendar_end: Calendar = Calendar.getInstance()
                calendar_end.set(year, month, day)

                val sdf_end = SimpleDateFormat("dd-MM-yyyy")
                val formatedDate_end: String = sdf_end.format(calendar.time)

                val lFirstName = lfname.text.toString().trim()
                val lLastName = llast.text.toString().trim()
                val lemaill = lemail.text.toString().trim()
                val lPhone = lphone.text.toString().trim()
                val tFirstName = tfirst.text.toString().trim()
                val tLastName = tlast.text.toString().trim()
                val temaill = temail.text.toString().trim()
                val tPhone = tphone.text.toString().trim()
                //val tOcuupants = tnumber_occupant.text.toString().trim()
                val country = country.text.toString().trim()
                val state = state.text.toString().trim()
                val city = citiy.text.toString().trim()
                val postal = postalcode.text.toString().trim()
                val startDate = formatedDate.trim()
                val endDate = formatedDate_end.trim()
                val payPeriod = pay_period.selectedItem.toString().trim()
                val rentAmount = rental_amount.text.toString().trim()
                val securityDeposit = security_deposit.text.toString().trim()
                val lateCharge = late_charges.text.toString().trim()
               val paymentMethod = payment_method.selectedItem.toString().trim()
                val collector = collector.text.toString().trim()

                if (lFirstName.isEmpty()) {
                    lfname.error = "first name required"
                    lfname.requestFocus()
                    return@setOnClickListener
                }
                if (lLastName.isEmpty()) {
                    llast.error = "last name required"
                    llast.requestFocus()
                    return@setOnClickListener
                }

                if (lemaill.isEmpty()) {
                    lemail.error = "Email required"
                    lemail.requestFocus()
                    return@setOnClickListener
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(lemaill).matches()){
                    lemail.error = "please input valid email"
                    lemail.requestFocus()
                    return@setOnClickListener

                }
                if (tFirstName.isEmpty()) {
                    tfirst.error = "first name required"
                    tfirst.requestFocus()
                    return@setOnClickListener
                }
                if (tLastName.isEmpty()) {
                    tlast.error = "last name required"
                    tlast.requestFocus()
                    return@setOnClickListener
                }
                if (temaill.isEmpty()) {
                    temail.error = "Email required"
                    temail.requestFocus()
                    return@setOnClickListener
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(temaill).matches()){
                    temail.error = "please input valid email"
                    temail.requestFocus()
                    return@setOnClickListener

                }






                //val securityDeposit = confirmp.text.toString().trim()

                val map = HashMap<String, String>()
                map["lFirstName"] = lFirstName
                map["lLastName"] = lLastName
                map["lemail"] =lemaill
                map["tFirstName"]=tFirstName
                map["tLastName"] = tLastName
                map["temail"] = temaill
                map["tPhone"] =tPhone
             //   map["tOcuupants"]=tOcuupants
                map["country"] = country
                map["state"] = state
                map["city"] =city
                map["postal"]=postal
                map["startDate"]=startDate
                map["endDate"] = endDate
                map["payPeriod"] = payPeriod
                map["rentAmount"] =rentAmount
                map["securityDeposit"]=securityDeposit
                map["lateCharge"] = lateCharge
                map["paymentMethod"] = paymentMethod
                map["collector"] =collector


                RetrofitClient.getApiService(this).postform(map)
                    .enqueue(object: Callback<DefaultResponse> {
                        override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                            Toast.makeText(applicationContext, "Successfully Inserted", Toast.LENGTH_LONG).show()
                        }

                    })



            }


     }
    }
