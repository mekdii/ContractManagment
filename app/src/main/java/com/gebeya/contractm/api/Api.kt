package com.gebeya.contractm.api


import com.gebeya.contractm.models.DefaultResponse
import com.gebeya.contractm.models.LoginResponse
import com.gebeya.contractm.models.userResponse
import retrofit2.Call
import retrofit2.http.*


interface Api {

//    @FormUrlEncoded
//  //  @Headers("Content-Type: application/json")
//    @POST("/users/signup")
//    fun createUser(
//        @Field("firstName")firstName:String,
//        @Field("lastName")lastName:String,
//        @Field("email")email:String,
//        @Field("password")password:String
//    ):Call<DefaultResponse>
       @POST("/users/signup")
       fun createUser(@Body map: HashMap<String, String>): Call<DefaultResponse>


    @POST("/users/login")
    fun loginUser(@Body map: HashMap<String, String>): Call<LoginResponse>

    @GET("/users/dashboard")
    fun fetchPosts(): Call<userResponse>



//    @FormUrlEncoded
//    @POST("/users/login")
//    fun userLogin(
//        @Field("email") email:String,
//        @Field("password") password: String
//    ):Call<LoginResponse>
}
