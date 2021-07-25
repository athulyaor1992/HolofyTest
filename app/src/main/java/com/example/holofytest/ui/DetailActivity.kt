package com.example.holofytest.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.holofytest.R
import com.example.holofytest.adapter.DetailAdapter
import com.example.holofytest.model.Videos
import com.master.exoplayer.MasterExoPlayerHelper
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.videoName


class DetailActivity : AppCompatActivity() {

    private lateinit var holofyVideos: Videos
    private lateinit var adapter: DetailAdapter
    private  var users: ArrayList<Videos> = ArrayList()
    private lateinit var masterExoPlayerHelper1: MasterExoPlayerHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        holofyVideos = intent.getParcelableExtra("videoList")!!

        users.add(holofyVideos)
        masterExoPlayerHelper1 = MasterExoPlayerHelper(mContext = this@DetailActivity, id = R.id.masterExoPlayer,useController = true)
        masterExoPlayerHelper1.makeLifeCycleAware(this)

        videoView1.layoutManager = LinearLayoutManager(this@DetailActivity)
        adapter = DetailAdapter(users)
        videoView1.adapter = adapter
        masterExoPlayerHelper1.attachToRecyclerView(videoView1)

        videoName.text = holofyVideos!!.title +"--"+ holofyVideos!!.subtitle
        videoDesc.text = holofyVideos!!.description

    }

}

