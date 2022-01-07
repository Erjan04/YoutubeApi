package com.example.youtubeapi

import android.app.Application
import com.example.youtubeapi.core.network.RetrofitClient
import com.example.youtubeapi.repository.Repository

class App : Application() {

    val repository by lazy { Repository() }
    val api by lazy { RetrofitClient.create() }
}