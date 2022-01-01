package com.example.youtubeapi.remote

import com.example.youtubeapi.BuildConfig.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private fun getLoggingInterceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private fun getOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(getLoggingInterceptor())
        .build()

    private fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val youtubeApi: YoutubeApi = getClient().create(YoutubeApi::class.java)
}