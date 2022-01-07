package com.example.youtubeapi.core.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.App
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.models.PlayList

open class BaseViewModel : ViewModel() {

    fun getPlaylists(): LiveData<Resource<PlayList>>{
        return App().repository.createPlayList();
    }

}