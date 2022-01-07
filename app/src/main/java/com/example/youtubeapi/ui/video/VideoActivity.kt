package com.example.youtubeapi.ui.video

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.R
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.ui.BaseActivity
import com.example.youtubeapi.databinding.ActivityVideoBinding
import com.example.youtubeapi.extensions.showToast
import com.example.youtubeapi.extensions.visible
import com.example.youtubeapi.models.Items
import com.example.youtubeapi.ui.player.PlayerActivity
import com.example.youtubeapi.utils.Connectivity
import com.example.youtubeapi.utils.`object`.Constant.KEY

class VideoActivity : BaseActivity<VideoViewModel, ActivityVideoBinding>() {

    private lateinit var id: String
    private val adapter: VideoAdapter by lazy {
        VideoAdapter(list)
    }
    private var list = mutableListOf<Items>()

    override fun inflateVB(inflater: LayoutInflater): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(inflater)
    }

    override fun initListener() {
        super.initListener()
        binding.tvBack.setOnClickListener {
            finish()
        }
        binding.clInet.btnAgain.setOnClickListener {
            checkInet()
        }
        binding.fabPlay.setOnClickListener {
            Intent(this@VideoActivity, PlayerActivity::class.java).apply {
                putExtra(KEY, list[0].id)
                startActivity(this)
            }
        }
    }

    override fun initView() {
        super.initView()
        viewModel = ViewModelProvider(this).get(VideoViewModel::class.java)
        checkInet()
        id = intent.getStringExtra("idKey").toString()
        binding.tvDescription.text = intent.getStringExtra("description")
        binding.tvTitlePlaylist.text = intent.getStringExtra("title")
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.loading.observe(this) {
            binding.progressBar.visible = it
        }
        viewModel.getPlaylistsVideo(id).observe(this) {
            when (it.status) {
                Status.LOADING -> viewModel.loading.postValue(true)
                Status.SUCCESS -> {
                    viewModel.loading.postValue(false)
                    initAdapter(it.data?.items as MutableList<Items>)
                    adapter.setOnClick(object : VideoAdapter.OnClick {
                        override fun onClick(items: Items) {
                            Intent(this@VideoActivity, PlayerActivity::class.java).apply {
                                putExtra(KEY, items.id)
                                startActivity(this)
                            }
                        }
                    })
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                    showToast(getString(R.string.error))
                }
            }
        }
    }

    private fun initAdapter(list: MutableList<Items>) {
        this.list = list
        binding.rvVideo.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@VideoActivity.adapter
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkInet() {
        val connect = Connectivity(this)
        connect.observe(this, { isConnected ->
            if (isConnected) {
                viewModel.loading.observe(this) {
                    binding.progressBar.visible = it
                }
                binding.rvVideo.visible = true
                binding.appBar.visible = true
                binding.fabPlay.visible = true
                binding.clInet.containerCl.visible = false
                viewModel.loading.postValue(false)
                viewModel.getPlaylistsVideo(id).observe(this) {
                    when (it.status) {
                        Status.LOADING -> viewModel.loading.postValue(true)
                        Status.SUCCESS -> {
                            viewModel.loading.postValue(false)
                            initAdapter(it.data?.items as MutableList<Items>)
                            showToast(getString(R.string.connected))
                            binding.tvCountVideo.text =
                                it.data.items.size.toString() + getString(R.string.video_series)
                        }
                        Status.ERROR -> {
                            viewModel.loading.postValue(false)
                            binding.clInet.containerCl.visible = true
                        }
                    }
                }
            } else {
                binding.rvVideo.visible = false
                binding.clInet.containerCl.visible = true
                binding.appBar.visible = false
                binding.fabPlay.visible = false
                showToast(getString(R.string.disconnected))
            }
        })
    }
}