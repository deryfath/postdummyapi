package com.cobamvvm.project.data

import com.cobamvvm.project.data.api.ApiService
import com.cobamvvm.project.data.local.DataBase
import com.cobamvvm.project.data.model.MovieResponse
import com.cobamvvm.project.data.model.postResponse

class DataRepository(private val apiDataRepository: ApiService,
                     private val localDataSource: DataBase) {


    suspend fun getPostNews(curPage:Int): postResponse {
        return apiDataRepository.getPostResponse(curPage).await()
    }

    suspend fun getMoviePopular(curPage:Int): MovieResponse {
        return apiDataRepository.getMoviePopular(curPage).await()
    }


}