package com.example.youtubeapi.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.youtubeapi.models.Items

class YoutubeDataSource : PagingSource<Int, Items>() {
    override fun getRefreshKey(state: PagingState<Int, Items>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Items> {
        TODO("Not yet implemented")
    }
}