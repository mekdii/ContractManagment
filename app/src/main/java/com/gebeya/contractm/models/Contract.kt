package com.gebeya.contractm.models

import java.util.*

//data class Contract( val category: String, val start_date : String, val Busines_partner: String , val status: String)

data class Contract(val _id : String, val lFirstName : String, val lLastName: String , val lemail: String
                    ,  val lPhone :String , val address: String, val tFirstName : String , val tLastName : String ,
                    val temail :String ,val tPhone : String , val country : String , val state: String , val  city: String,
                    val  postal : String , val startDate : String , val   endDate: String , val  payPeriod: String,
                    val  rentAmount : String , val securityDeposit :String , val lateCharge: String , val paymentMethod: String,
                    val collector : String , val  terms: String , val lSignature : String , val tSignature: String , val date : Date

)