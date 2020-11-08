package com.gebeya.contractm.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gebeya.contractm.R
import com.gebeya.contractm.api.RetrofitClient
import com.gebeya.contractm.models.LoginResponse
import com.gebeya.contractm.models.userResponse
import com.gebeya.contractm.storage.SharedPrefManager
import com.gebeya.contractm.storage.SharedPrefManager.Companion.USER_NAME
import com.gebeya.contractm.storage.SharedPrefManager.Companion.USER_TOKEN
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Navigation : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    private lateinit var apiClient: RetrofitClient
    private lateinit var sessionManager: SharedPrefManager

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        nav_view.setNavigationItemSelectedListener(this);

        apiClient = RetrofitClient
        sessionManager = SharedPrefManager(this)


        val navigationView =
            findViewById<View>(R.id.nav_view) as NavigationView
        val hView = navigationView.getHeaderView(0)
        val nav_user = hView.findViewById(R.id.nav_text) as TextView
        nav_user.setOnClickListener {
            fetchPosts()

        }
        nav_user.setText(sessionManager.fetchAuthToken()?.getValue(USER_NAME))



        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)


        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow , R.id.user2 , R.id.logoutt
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
          private fun fetchPosts() {

        RetrofitClient.getApiService(this).fetchPosts().enqueue(object: Callback<LoginResponse> {
            override fun onFailure(p0: Call<LoginResponse>, p1: Throwable) {
                Toast.makeText(applicationContext, "on failer", Toast.LENGTH_LONG).show()


            }

            override fun onResponse(p0: Call<LoginResponse>, p1: Response<LoginResponse>) {
                //  Toast.makeText(applicationContext, "${sessionManager.fetchAuthToken()}", Toast.LENGTH_LONG).show()
                val userResponse = p1.body()
                if (userResponse != null) {
                    Toast.makeText(applicationContext, "{${userResponse.firstName}}", Toast.LENGTH_LONG).show()
                }


            }
        })
    }
//        override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        var itemview = item.itemId
//        when(itemview){
//            R.id.search -> Toast.makeText(applicationContext , "this is search" , Toast.LENGTH_SHORT).show()
//            R.id.logout -> Toast.makeText(applicationContext , "person clicked" , Toast.LENGTH_SHORT).show()
//        }
//        return true
//    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
      //  p0.isChecked = true;
       // var itemview = p0.itemId
        when(p0.itemId){
          //  R.id.search -> Toast.makeText(applicationContext , "this is search" , Toast.LENGTH_SHORT)
            //R.id.logoutt -> Toast.makeText(applicationContext , "person clicked" , Toast.LENGTH_SHORT).show()
            R.id.nav_home ->Toast.makeText(applicationContext , "person clicked" , Toast.LENGTH_SHORT).show()
           // Log.d("" , "hi")
        }
        return true

    }
}