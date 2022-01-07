package com.example.youtubeapi.ui.playlists

import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.R
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.ui.BaseActivity
import com.example.youtubeapi.databinding.ActivityPlaylistBinding
import com.example.youtubeapi.extensions.showToast
import com.example.youtubeapi.extensions.visible
import com.example.youtubeapi.models.Items
import com.example.youtubeapi.ui.video.VideoActivity
import com.example.youtubeapi.utils.Connectivity
import com.example.youtubeapi.utils.`object`.Constant.KEY

class PlaylistActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistBinding>() {

    private val adapter: PlaylistAdapter by lazy {
        PlaylistAdapter(list)
    }
    private var list = mutableListOf<Items>()

    override fun initView() {
        super.initView()
        checkInet()
        viewModel =
            ViewModelProvider(this).get(PlaylistViewModel::class.java).also { viewModel = it }
    }

    override fun initListener() {
        super.initListener()
        binding.clInet.btnAgain.setOnClickListener {
            checkInet()
        }
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel.loading.observe(this) {
            binding.progressBar.visible = it
        }

        viewModel.getPlaylist().observe(this) {
            when (it.status) {
                Status.LOADING -> viewModel.loading.postValue(true)
                Status.SUCCESS -> {
                    viewModel.loading.postValue(false)
                    initAdapter(it.data?.items as MutableList<Items>)
                    adapter.setOnClick(object : PlaylistAdapter.OnClick {
                        override fun onClicked(position: Items) {
                            Intent(this@PlaylistActivity, VideoActivity::class.java).apply {
                                putExtra("idKey", position.id)
                                putExtra("title", position.snippet.title)
                                putExtra("description", position.snippet.description)
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


    override fun inflateVB(inflater: LayoutInflater): ActivityPlaylistBinding {
        binding = ActivityPlaylistBinding.inflate(inflater)
        return binding
    }

    private fun initAdapter(list: MutableList<Items>) {
        this.list = list
        binding.rvPlaylists.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PlaylistActivity.adapter
        }
    }

    private fun checkInet() {
        val connect = Connectivity(this)
        connect.observe(this, { isConnected ->
            if (isConnected) {
                viewModel.loading.observe(this) {
                    binding.progressBar.visible = it
                }
                binding.rvPlaylists.visible = true
                binding.clInet.containerCl.visible = false
                viewModel.loading.postValue(false)
                viewModel.getPlaylist().observe(this) {
                    when (it.status) {
                        Status.LOADING -> viewModel.loading.postValue(true)
                        Status.SUCCESS -> {
                            viewModel.loading.postValue(false)
                            showToast(getString(R.string.connected))
                        }
                        Status.ERROR -> {
                            viewModel.loading.postValue(false)
                            binding.clInet.containerCl.visible = true
                        }
                    }
                }
            } else {
                binding.rvPlaylists.visible = false
                binding.clInet.containerCl.visible = true
                showToast(getString(R.string.disconnected))
            }
        })
    }
}
