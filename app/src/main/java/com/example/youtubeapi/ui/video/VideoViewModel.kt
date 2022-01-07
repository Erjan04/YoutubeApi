package com.example.youtubeapi.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.ui.BaseViewModel
import com.example.youtubeapi.models.PlayList

class VideoViewModel : BaseViewModel() {

    var loading = MutableLiveData<Boolean>()

    fun getPlaylistsVideo(playlistId: String): LiveData<Resource<PlayList>> {
        return App().repository.createPlayItem(playlistId)
    }
}