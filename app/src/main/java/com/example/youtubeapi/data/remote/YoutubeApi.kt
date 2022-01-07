package com.example.youtubeapi.data.remote

import com.example.youtubeapi.models.PlayList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apikey: String,
        @Query("maxResults") maxResults: Int,
    ): Response<PlayList>

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") part: String,
        @Query("playlistId") channelId: String,
        @Query("key") apikey: String,
        @Query("maxResults") maxResults: Int,
    ): Response<PlayList>
}