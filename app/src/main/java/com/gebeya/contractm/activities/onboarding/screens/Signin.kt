package com.gebeya.contractm.activities.onboarding.screens

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gebeya.contractm.R
import com.gebeya.contractm.activities.Navigation
import com.gebeya.contractm.api.RetrofitClient
import com.gebeya.contractm.framework.util.PASSWORD_PATTERN
import com.gebeya.contractm.models.LoginResponse
import com.gebeya.contractm.storage.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_signin.*
import kotlinx.android.synthetic.main.fragment_signin.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class Signin : Fragment() {



    var mIsShowPass = false
    private lateinit var sessionManager: SharedPrefManager
    private lateinit var apiClient: RetrofitClient



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_signin, container, false)
//        view.ivShowHidePass.setOnClickListener {
//            mIsShowPass = !mIsShowPass
//            showPassword(mIsShowPass)
//        }
//
//        showPassword(mIsShowPass)

        apiClient = RetrofitClient
        sessionManager = activity?.let { SharedPrefManager(it) }!!
        //code for showing and hiding password
        view.ivShowHidePass.setOnClickListener {
            mIsShowPass = !mIsShowPass
            if ( mIsShowPass) {
                // To show the password
                view.editTextPasswor.transformationMethod = HideReturnsTransformationMethod.getInstance()
                view.ivShowHidePass.setImageResource(R.drawable.hide)
            } else {
                // To hide the password
                view.editTextPasswor.transformationMethod = PasswordTransformationMethod.getInstance()
                view.ivShowHidePass.setImageResource(R.drawable.show)
            }
            // This line of code to put the pointer at the end of the password string
            view.editTextPasswor.setSelection(editTextPasswor.text.toString().length)
            //showPassword(mIsShowPass)
        }
      //  showPassword(mIsShowPass)


        view.signup.setOnClickListener {
           // findNavController().navigate(R.id.action_signin_to_registor)
            val ft = fragmentManager!!.beginTransaction()
            ft.replace(R.id.fragment, Registor(), "NewFragmentTag")
            ft.commit()
        }
        view.buttonLogin.setOnClickListener {

            val email = editTextEmail.text.toString().trim()
            val password = editTextPasswor.text.toString().trim()

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
                editTextPasswor.error = "Password required"
                editTextPasswor.requestFocus()
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

            activity?.let { it1 ->
                RetrofitClient.getApiService(it1).loginUser(map)
                    .enqueue(object: Callback<LoginResponse> {
                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            val loginResponse = response.body()

                            if (loginResponse?.success == true) {
                                if (loginResponse != null) {

                                    sessionManager.saveAuthToken(loginResponse.token , loginResponse.firstName , loginResponse.lastName)
                                    Toast.makeText(
                                        activity,
                                        "successful",
                                        Toast.LENGTH_LONG
                                    ).show()

                                   // findNavController().navigate(R.id.action_signin_to_nav_slideshow)
                                    activity?.let{
                                        val intent = Intent (it, Navigation::class.java)
                                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        it.startActivity(intent)
                                    }

                                }

                            } else {
                                Toast.makeText(
                                    activity,
                                    "something went wrong",
                                    Toast.LENGTH_LONG
                                ).show()
                                // Error logging in
                            }
                        }
                    })}}
            return view
        }


    override fun onStart() {
        super.onStart()
        if (sessionManager.isLoggedIn()){
            activity?.let{
                val intent = Intent (it, Navigation::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                it.startActivity(intent)
            }

        }

    }

}


