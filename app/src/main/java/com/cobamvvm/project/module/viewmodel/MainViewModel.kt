package com.cobamvvm.project.module.viewmodel

import androidx.lifecycle.MutableLiveData
import com.cobamvvm.project.data.DataRepository
import com.cobamvvm.project.data.model.Data
import com.cobamvvm.project.data.model.Movie
import com.cobamvvm.project.data.model.MovieResult
import com.cobamvvm.project.data.model.postResponse
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DataRepository):AbstractVIewModel() {

    val postLiveData = MutableLiveData<MutableList<Data>>()

    fun getMovieHome(curPage:Int){
        launch {
            try {
                setLoading()


                val dataPost = repository.getPostNews(curPage)
                postLiveData.postValue(dataPost.data)


            }catch (t:Throwable){
                println("ERROR COROUTINE "+t.message)

                setError(t)
            }finally {
                setLoading(false)
            }
        }
    }

    fun start(){
        postLiveData.value = mutableListOf()
    }
}