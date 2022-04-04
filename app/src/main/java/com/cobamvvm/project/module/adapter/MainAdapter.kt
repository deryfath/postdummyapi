package com.cobamvvm.project.module.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cobamvvm.project.R
import com.cobamvvm.project.Utils.Constants.Companion.IMAGE_BASE_URL
import com.cobamvvm.project.data.model.Data
import com.cobamvvm.project.data.model.MovieResult
import com.cobamvvm.project.module.DetailActivity
import kotlinx.android.extensions.LayoutContainer

class MainAdapter (private var items:MutableList<Data>, private val context: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view=holder.itemView
        val data=items[position]

        view.let {
            it.visibility = View.VISIBLE

            (it.findViewById(R.id.tv_title) as TextView).text = data.text
            (it.findViewById(R.id.tv_likes) as TextView).text = "likes : "+data.likes.toString()
            (it.findViewById(R.id.tv_release_date) as TextView).text = "Release Date : "+data.publishDate
            Glide.with(context).load(data.image).into(it.findViewById(R.id.iv_movie) as ImageView)

        }

//        view.setOnClickListener {
//            val intent = Intent(context,DetailActivity::class.java)
//            intent.putExtra("movie_detail",data)
//            context.startActivity(intent)
//        }

    }

    fun addItems(list:MutableList<Data>){
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {


    }
}