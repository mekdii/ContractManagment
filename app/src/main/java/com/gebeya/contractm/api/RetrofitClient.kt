package com.gebeya.contractm.api
import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.5:3000"
   // private const val BASE_URL = "http://contract-managment.apps.et6ea.gebeya.co"

    private lateinit var apiService: Api



    fun getApiService(context: Context): Api {

        // Initialize ApiService if not initialized yet
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient(context))
                .build()

            apiService = retrofit.create(Api::class.java)
        }

        return apiService
    }

    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }






//    private val AUTH = "jwt"
//
//    private const val BASE_URL = "http://192.168.1.5:3000"
//
//
//    private val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor { chain ->
//            val original = chain.request()
//
//            val requestBuilder = original.newBuilder()
//                .addHeader("Authorization", AUTH)
//                .method(original.method(), original.body())
//
//            val request = requestBuilder.build()
//            chain.proceed(request)
//        }.build()
//
//    val instance: Api by lazy {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//
//        retrofit.create(Api::class.java)
//    }
}