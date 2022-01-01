package com.example.youtubeapi.ui.playlists

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.`object`.Constant
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.models.PlayList
import com.example.youtubeapi.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel : BaseViewModel() {
    private val api = RetrofitClient.create()

    fun getPlaylist(): LiveData<PlayList> {
        return createPlayList()
    }

    private fun createPlayList(): LiveData<PlayList> {

        val data = MutableLiveData<PlayList>()

        api.getPlaylists(Constant.PART, Constant.CHANNEL_ID, BuildConfig.API_KEY)
            .enqueue(object : Callback<PlayList> {
                override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                    if (response.isSuccessful && response.body() != null) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<PlayList>, t: Throwable) {
                    Log.e("TAG", "onFailure: " + t.localizedMessage)
                }
            })
        return data
    }
}