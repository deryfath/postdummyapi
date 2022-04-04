package com.cobamvvm.project.module

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cobamvvm.project.R
import com.cobamvvm.project.Utils.EndlessScrollListener
import com.cobamvvm.project.Utils.observe
import com.cobamvvm.project.module.adapter.MainAdapter
import com.cobamvvm.project.module.viewmodel.MainViewModel
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel


class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: MainAdapter
    private var currentPage = 1;
    private val handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        setContentView(R.layout.activity_main)
        Glide.with(this)
            .load(R.drawable.photo_female_7)
            .apply(RequestOptions.circleCropTransform())
            .into(image);
        init()
    }

    private fun init(){
        viewModel.start()
        pb_main.visibility = View.GONE
        viewModel.getMovieHome(currentPage)

        appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(
                appBarLayout: AppBarLayout?,
                verticalOffset: Int
            ) {
                if (collapsing_toolbar.getHeight() + verticalOffset < 2 * ViewCompat.getMinimumHeight(collapsing_toolbar))
                {
                    layout_profile.visibility = View.GONE
                } else {
                    layout_profile.visibility = View.VISIBLE
                }
            }
        })


        observe(viewModel.postLiveData, {
            println("LIST "+it)

            if (currentPage==1){
                adapter = MainAdapter(it!!,this)
                val mLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
                rv_movie.setLayoutManager(mLayoutManager)
                rv_movie.adapter = adapter
            }else{
                pb_main.visibility = View.VISIBLE

                adapter.addItems(it!!)


            }
            rv_movie.addOnScrollListener(loadScrollData())


        })

        observe(viewModel.isDataLoading,{
            if (it == true){
                println("LOADING")
            }else{
                handler.postDelayed(Runnable {
                    pb_main.visibility = View.GONE

                }, 3000)
                println("DONE")
            }
        })
    }

    private fun loadScrollData():EndlessScrollListener{

        return object : EndlessScrollListener(){
            override fun onLoadMore() {
                viewModel.getMovieHome(currentPage++)



            }
        }
    }


}
