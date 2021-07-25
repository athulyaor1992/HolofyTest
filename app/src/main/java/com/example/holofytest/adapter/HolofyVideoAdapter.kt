package com.example.holofytest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.holofytest.R
import com.example.holofytest.model.HolofyModel
import com.example.holofytest.model.Videos
import com.master.exoplayer.ExoPlayerHelper
import kotlinx.android.synthetic.main.holofy_item.view.*


class HolofyVideoAdapter(
    private val users: ArrayList<Videos>,
    private val cellClickListener: CellClickListener
) : RecyclerView.Adapter<HolofyVideoAdapter.DataViewHolder>() {

    interface CellClickListener {
        fun onCellClickListener(data: Videos)
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: Videos) {
            itemView.apply {

                videoName.text = user.title
                masterExoPlayer.url = user.sources[0]
                masterExoPlayer.imageView = image

                var IMG_URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/"

                Glide.with(this)
                    .load(IMG_URL +user.thumb)
                    .into(image);

                masterExoPlayer.listener = object : ExoPlayerHelper.Listener {
                    override fun onBuffering(isBuffering: Boolean) {
                        super.onBuffering(isBuffering)
                        Log.e("TAG", isBuffering.toString())
                    }
                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.holofy_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position]!!)

        val data = users[position]

        holder.itemView.setOnClickListener{
            cellClickListener.onCellClickListener(data)
        }

    }

    fun addUsers(users: List<Videos>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}