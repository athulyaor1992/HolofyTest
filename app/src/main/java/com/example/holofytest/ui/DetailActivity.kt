package com.example.holofytest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.holofytest.R
import com.example.holofytest.model.Videos
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.master.exoplayer.ExoPlayerHelper
import com.master.exoplayer.MasterExoPlayerHelper
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.holofy_item.view.*

class DetailActivity : AppCompatActivity() {

    var holofyVideos: Videos? = null
    lateinit var masterExoPlayerHelper: MasterExoPlayerHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        holofyVideos = intent.getParcelableExtra("videoList")

 /*       masterExoPlayerHelper = MasterExoPlayerHelper(mContext = this, id = R.id.masterExoPlayer1,useController = true, defaultMute = true)
        masterExoPlayerHelper.makeLifeCycleAware(this)

        //Used to customize attributes
        masterExoPlayerHelper.getPlayerView().apply {
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
        }*/


        if (holofyVideos != null) {

            Log.e("source", holofyVideos!!.sources[0])

            masterExoPlayer1.url = holofyVideos!!.sources[0]
            masterExoPlayer1.imageView = image

           // masterExoPlayerHelper.exoPlayerHelper.play()


            var IMG_URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/"

            Glide.with(this)
                .load(IMG_URL + holofyVideos!!.thumb)
                .into(image)

            videoName.text = holofyVideos!!.title +"--"+ holofyVideos!!.subtitle
            videoDesc.text = holofyVideos!!.description

 /*           masterExoPlayer1.listener = object : ExoPlayerHelper.Listener {
                override fun onBuffering(isBuffering: Boolean) {
                    super.onBuffering(isBuffering)

                    Log.e("TAG222", isBuffering.toString())
                }
            }*/

        }

    }
}