package com.example.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtubeapi.App
import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.models.PlayList
import com.example.youtubeapi.utils.`object`.Constant
import kotlinx.coroutines.Dispatchers

class Repository {

    fun createPlayList(): LiveData<Resource<PlayList>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response =
            App().api.getPlaylists(Constant.PART, Constant.CHANNEL_ID, BuildConfig.API_KEY, 20)
        if (response.isSuccessful && response.body() != null) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), response.body(), response.code()))
        }
    }

    fun createPlayItem(videoPlaylistId: String): LiveData<Resource<PlayList>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val responce =
                App().api.getPlaylistItems(Constant.PART, videoPlaylistId, BuildConfig.API_KEY, 20)
            if (responce.isSuccessful && responce.body() != null) {
                emit(Resource.success(responce.body()))
            } else {
                emit(Resource.error(responce.message(), responce.body(), responce.code()))
            }
        }
}