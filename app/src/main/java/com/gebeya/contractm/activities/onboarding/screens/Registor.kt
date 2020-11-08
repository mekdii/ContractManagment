package com.gebeya.contractm.activities.onboarding.screens

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.gebeya.contractm.R
import com.gebeya.contractm.activities.Navigation
import com.gebeya.contractm.api.RetrofitClient
import com.gebeya.contractm.framework.util.PASSWORD_PATTERN
import com.gebeya.contractm.models.DefaultResponse
import com.gebeya.contractm.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_registor.*
import kotlinx.android.synthetic.main.fragment_registor.*
import kotlinx.android.synthetic.main.fragment_registor.editTextEmail
import kotlinx.android.synthetic.main.fragment_registor.view.*
import kotlinx.android.synthetic.main.fragment_signin.*
import kotlinx.android.synthetic.main.fragment_signin.view.*
import kotlinx.android.synthetic.main.fragment_third_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Registor : Fragment() {
    private val fragment = Signin()
    var mIsShowPass = false
    private lateinit var sessionManager: SharedPrefManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registor, container, false)
        (context as AppCompatActivity).setSupportActionBar(tool)
        view.tool.setOnClickListener {
            Toast.makeText(context , "yaaay" , Toast.LENGTH_SHORT).show()
            val ft = fragmentManager!!.beginTransaction()
            ft.replace(R.id.fragment, fragment, "NewFragmentTag")
            ft.commit()
        }

        //code for showing and hiding password
        view.ivShowHidePas.setOnClickListener {
            mIsShowPass = !mIsShowPass
            if ( mIsShowPass) {
                // To show the password
                view.editTextPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                view.ivShowHidePas.setImageResource(R.drawable.hide)
            } else {
                // To hide the password
                view.editTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                view.ivShowHidePas.setImageResource(R.drawable.show)
            }
            // This line of code to put the pointer at the end of the password string
            view.editTextPassword.setSelection(editTextPassword.text.toString().length)
            //showPassword(mIsShowPass)
        }

        //code for showing and hiding confirmPassword
        view.ivShowHidecon.setOnClickListener {
            mIsShowPass = !mIsShowPass
            if ( mIsShowPass) {
                // To show the password
                view.confirmp.transformationMethod = HideReturnsTransformationMethod.getInstance()
                view.ivShowHidecon.setImageResource(R.drawable.hide)
            } else {
                // To hide the password
                view.confirmp.transformationMethod = PasswordTransformationMethod.getInstance()
                view.ivShowHidecon.setImageResource(R.drawable.show)
            }
            // This line of code to put the pointer at the end of the password string
            view.confirmp.setSelection(confirmp.text.toString().length)
            //showPassword(mIsShowPass)
        }


        sessionManager = activity?.let { SharedPrefManager(it) }!!

        view.buttonSignUp.setOnClickListener {
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



            activity?.let { it1 ->
                RetrofitClient.getApiService(it1).createUser(map)
                    .enqueue(object: Callback<DefaultResponse> {
                        override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                            Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                            Toast.makeText(activity, response.body()?.message, Toast.LENGTH_LONG).show()
                            //findNavController().navigate(R.id.action_registor_to_nav_slideshow)
                            sessionManager.saveAuthToken(password , firstName , lastName)
                            activity?.let{
                                val intent = Intent (it, Navigation::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                it.startActivity(intent)
                            }

                            finish
                        }

                    })
            }




        }





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