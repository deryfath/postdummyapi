package com.cobamvvm.project.data.api

import com.cobamvvm.project.data.model.MovieResponse
import com.cobamvvm.project.data.model.postResponse
import retrofit2.http.GET
import kotlinx.coroutines.Deferred
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiService {

    @Headers("app-id:624aa2c26c121308d83ccd79")
    @GET("/data/v1/post")
    fun getPostResponse(@Query("page") page:Int
    ):Deferred<postResponse>

    @GET("/3/movie/popular")
    fun getMoviePopular(@Query("page") page:Int):Deferred<MovieResponse>

    @GET("/3/movie/now_playing")
    fun getMovieNowPlaying():Deferred<MovieResponse>

    @GET("/3/movie/upcoming")
    fun getMovieUpcoming():Deferred<MovieResponse>


}