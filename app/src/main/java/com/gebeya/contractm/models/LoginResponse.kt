package com.gebeya.contractm.models



//data class LoginResponse(val success: Boolean, val token:String, val user: User)

data class LoginResponse(val success: Boolean, val token:String, val firstName: String , val lastName : String , val email: String)