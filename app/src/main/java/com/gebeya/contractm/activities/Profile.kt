package com.gebeya.contractm.activities

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gebeya.contractm.R
import com.gebeya.contractm.api.RetrofitClient
import com.gebeya.contractm.models.userResponse
import com.gebeya.contractm.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class
Profile : AppCompatActivity() {
    private lateinit var apiClient: RetrofitClient
    private lateinit var sessionManager: SharedPrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
       
        setSupportActionBar(toolbarr)
        toolbarr.setNavigationOnClickListener {
            Toast.makeText(applicationContext , "back icon clicked" , Toast.LENGTH_SHORT)
        }

        apiClient = RetrofitClient
        sessionManager = SharedPrefManager(this)
        show.setOnClickListener {
            first.text = "hi"
            fetchPosts()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu , menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemview = item.itemId
        when(itemview){
            R.id.search -> Toast.makeText(applicationContext , "this is search" , Toast.LENGTH_SHORT)
            R.id.person -> Toast.makeText(applicationContext , "person clicked" , Toast.LENGTH_SHORT)
        }
        return false
    }

    private fun fetchPosts() {

        RetrofitClient.getApiService(this).fetchPosts().enqueue(object: Callback<userResponse> {
            override fun onFailure(p0: Call<userResponse>, p1: Throwable) {
                Toast.makeText(applicationContext, "on failer", Toast.LENGTH_LONG).show()


            }

            override fun onResponse(p0: Call<userResponse>, p1: Response<userResponse>) {
              //  Toast.makeText(applicationContext, "${sessionManager.fetchAuthToken()}", Toast.LENGTH_LONG).show()
                val userResponse = p1.body()
                if (userResponse != null) {
                    Toast.makeText(applicationContext, "{${userResponse.user}}", Toast.LENGTH_LONG).show()
                }
//                if (userResponse != null) {
//                    first.text = userResponse.firstName
//                    last.text = userResponse.lastName
//                    email.text = userResponse.lastName
//
//                }
//                else{
//                    Toast.makeText(applicationContext, "else else", Toast.LENGTH_LONG).show()
//
//                }



            }
        })
    }


}