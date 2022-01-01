package com.example.youtubeapi.remote

import com.example.youtubeapi.models.PlayList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apikey: String,
    ): Call<PlayList>
}