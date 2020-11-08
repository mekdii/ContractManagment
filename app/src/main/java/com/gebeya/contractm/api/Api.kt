package com.gebeya.contractm.api


import com.gebeya.contractm.models.Contract
import com.gebeya.contractm.models.DefaultResponse
import com.gebeya.contractm.models.LoginResponse
import com.gebeya.contractm.models.userResponse
import retrofit2.Call
import retrofit2.http.*
import java.util.ArrayList


interface Api {


    @POST("/users/signup")
    fun createUser(@Body map: HashMap<String, String>): Call<DefaultResponse>


    @POST("/users/login")
    fun loginUser(@Body map: HashMap<String, String>): Call<LoginResponse>

    @GET("/users/dashboard")
    fun fetchPosts(): Call<LoginResponse>

    @POST("/forms/rental")
    fun postform(@Body map: HashMap<String, String>): Call<DefaultResponse>

    @GET("/forms/rental")
    fun fetchRentals(): Call<ArrayList<Contract>>

    @DELETE("/forms/rental/{id}")
    fun deleteForm(@Path("id") id : String): Call<String>

    @PUT("/forms/rental/{id}")
    fun updateForm(@Path("id") id : String , @Body  map: HashMap<String, String>): Call<String>






}
