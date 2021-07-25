package com.example.holofytest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.holofytest.R
import com.example.holofytest.adapter.HolofyVideoAdapter
import com.example.holofytest.model.Videos
import com.example.holofytest.util.Status
import com.example.holofytest.util.snackbar
import com.example.holofytest.viewmodel.HolofyViewModel
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.master.exoplayer.MasterExoPlayerHelper
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*


open class MainActivity : AppCompatActivity(), HolofyVideoAdapter.CellClickListener  {

    private lateinit var viewModel: HolofyViewModel
    private lateinit var adapter: HolofyVideoAdapter
    private lateinit var masterExoPlayerHelper: MasterExoPlayerHelper

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(HolofyViewModel::class.java)

        setupUI()
        setupObservers()

    }

    private fun setupUI() {

        masterExoPlayerHelper = MasterExoPlayerHelper(mContext = this, id = R.id.masterExoPlayer,useController = true, defaultMute = true)
        masterExoPlayerHelper.makeLifeCycleAware(this)

        //Used to customize attributes
        masterExoPlayerHelper.getPlayerView().apply {
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
        }

        videoView.layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = HolofyVideoAdapter(arrayListOf(),this)
        videoView.adapter = adapter

        masterExoPlayerHelper.attachToRecyclerView(videoView)
    }

    private fun setupObservers() {
        val view = window.decorView.rootView
        viewModel.getVideos().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        videoView.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(resource.data!!.body()!!.result[0].videos  as ArrayList<Videos>) }
                    }
                    Status.ERROR -> {
                        videoView.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                        view.snackbar(this,resource.message.toString())
                    }
                    Status.LOADING -> {
                        videoView.visibility = View.VISIBLE
                        progress_bar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private  fun retrieveList(users: ArrayList<Videos>) {

        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }

    }

    override fun onCellClickListener(data: Videos) {

        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("videoList", data)
        val transitionName = getString(R.string.transition_string)

        val viewStart = findViewById<View>(R.id.card_view)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this@MainActivity,
            viewStart,  // Starting view
            transitionName // The String
        )
        ActivityCompat.startActivity(this@MainActivity, intent, options.toBundle())
    }

}

