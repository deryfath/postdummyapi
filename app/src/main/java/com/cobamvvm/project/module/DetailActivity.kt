package com.cobamvvm.project.module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cobamvvm.project.R
import com.cobamvvm.project.Utils.Constants
import com.cobamvvm.project.data.model.MovieResult
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_movie.*

class DetailActivity : AppCompatActivity() {

    lateinit var movieData : MovieResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initToolbar()
        initData()

    }

    private fun initData(){
        movieData = intent.getSerializableExtra("movie_detail") as MovieResult

        Glide.with(this).load(Constants.IMAGE_BASE_URL +movieData.poster_path).into(iv_movie_detail)
        tv_title_detail.text = movieData.title
        tv_date.text = movieData.release_date
        tv_genre.text = movieData.vote_average.toString()
        tv_desc.text = movieData.overview
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_detail)
        supportActionBar?.title = ""
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_detail.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onBackPressed()
            }
        })
    }
}
