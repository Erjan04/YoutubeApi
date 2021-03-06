package com.example.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.ui.BaseViewModel
import com.example.youtubeapi.models.PlayList

class PlaylistViewModel : BaseViewModel() {

    var loading = MutableLiveData<Boolean>()

    fun getPlaylist(): LiveData<Resource<PlayList>> {
        return App().repository.createPlayList()
    }
}