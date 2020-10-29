package com.gebeya.contractm.models



data class LoginResponse(val success: Boolean, val token:String, val user: User)