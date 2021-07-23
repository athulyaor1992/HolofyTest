package com.example.holofytest

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.holofytest.network.ApiService
import com.example.holofytest.network.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.properties.Delegates

class App  : Application() {

    var BASE_URL = "https://run.mocky.io/v3/"



    companion object {
        var api: ApiService? = null
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        MultiDex.install(this)
        val interceptor = NetworkConnectionInterceptor(this)

        val okkHttpClient =OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okkHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiService::class.java)


    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


}