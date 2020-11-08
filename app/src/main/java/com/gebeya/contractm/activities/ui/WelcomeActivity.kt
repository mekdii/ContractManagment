package com.gebeya.contractm.activities.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.gebeya.contractm.R
import com.gebeya.contractm.activities.Login
import com.gebeya.contractm.activities.MainActivity
import com.gebeya.contractm.framework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_welcome.*


const val PAGE_COUNT = 3

class WelcomeActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    private lateinit var titles: Array<String>
    private lateinit var descriptions: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val preferences = getSharedPreferences(
            "my_preferences",
            Context.MODE_PRIVATE
        )
        if (preferences.getBoolean("onboarding_complete", false)) {
            // Start Main Activity
            val intent = Intent(this, Login::class.java)
            startActivity(intent)

            // Close Onboarding
            finishTutorial()
            return
        }


//        if(isopened()){
//            val intent = Intent(this , Login::class.java)
//            startActivity(intent)
//
//        }
//        else{
//            val sharedPref = getSharedPreferences("onBoarding", Context.MODE_PRIVATE).edit()
//
//            //return sharedPref.getBoolean("Finished", false)
//
//        }
        welcomeSkipButton.setOnClickListener {
            Toast.makeText(this , "mekdi" , Toast.LENGTH_LONG).show()



        }


        descriptions = resources.getStringArray(R.array.welcome_descriptions)

        val images = arrayOf(
            R.drawable.mek,
            R.drawable.mek,
            R.drawable.mek
        )



        welcomeViewPager.adapter = WelcomePagerAdapter(supportFragmentManager, images)

        welcomeViewPager.addOnPageChangeListener(this)

/*
        val handler = Handler()
        val task = object: Runnable {
            override fun run() {
                moveNext()
                handler.postDelayed(this, 3000)
            }
        }
        handler.postDelayed(task, 3000)
*/
    }
//    fun isopened(): Boolean {
//
//    }
    fun finishTutorial(){

    val preferences =
        getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

    // Set onboarding_complete to true

    // Set onboarding_complete to true
    preferences.edit()
        .putBoolean("onboarding_complete", true).apply()

    // Launch the main Activity, called MainActivity

    // Launch the main Activity, called MainActivity
    val main = Intent(this, Login::class.java)
    startActivity(main)

    // Close the OnboardingActivity

    // Close the OnboardingActivity
    finish()

}




    // Timer class

    fun moveNext() {
        val position = welcomeViewPager.currentItem
        val next = if (position == PAGE_COUNT - 1) {
            0
        } else {
            position + 1
        }

        welcomeViewPager.currentItem = next
    }

    override fun onPageScrollStateChanged(state: Int) = Unit

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

        val description = descriptions[position]

        //welcomeTitleLabel.text = title
        welcomeDescriptionLabel.text = description
    }
}
