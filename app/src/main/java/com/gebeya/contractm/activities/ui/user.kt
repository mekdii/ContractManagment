package com.gebeya.contractm.activities.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gebeya.contractm.R
import com.gebeya.contractm.activities.Navigation
import com.gebeya.contractm.activities.onboarding.screens.Registor
import com.gebeya.contractm.activities.onboarding.screens.Signin
import com.gebeya.contractm.storage.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_user.view.*


class user : Fragment() {
    private lateinit var sessionManager: SharedPrefManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_user, container, false)
        sessionManager = activity?.let { SharedPrefManager(it) }!!
        view.signout.setOnClickListener {

            //logout()
            val ft = fragmentManager!!.beginTransaction()
            ft.replace(R.id.fragment, Signin(), "NewFragmentTag")
            ft.commit()
           // activity?.onBackPressed();
//            activity?.let{
//                val intent = Intent (it, Signin::class.java)
//                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                it.startActivity(intent)
//            }

        }
        return view
    }


    fun logout(){
        sessionManager.clear()

    }



}