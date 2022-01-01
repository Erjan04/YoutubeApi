package com.example.youtubeapi.ui.playlists

import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.R
import com.example.youtubeapi.`object`.Constant.KEY
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.ActivityPlaylistBinding
import com.example.youtubeapi.extensions.showToast
import com.example.youtubeapi.extensions.visible
import com.example.youtubeapi.models.Items
import com.example.youtubeapi.ui.video.VideoActivity

class PlaylistActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistBinding>() {

    private lateinit var adapter: PlaylistAdapter

    override fun initView() {
        super.initView()
        viewModel =
            ViewModelProvider(this).get(PlaylistViewModel::class.java).also { viewModel = it }
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.getPlaylist().observe(this) {
            adapter = PlaylistAdapter(it)
            binding.rvPlaylists.adapter = adapter
            adapter.setOnClick(object : PlaylistAdapter.OnClick {
                override fun onClicked(position: Items) {
                    val intent = Intent(this@PlaylistActivity, VideoActivity::class.java).apply {
                        putExtra(KEY, position.id)
                    }
                    startActivity(intent)
                }
            })
        }
    }

    override fun checkInet() {
        val connect = Connectivity(this)
        connect.observe(this, { isConnected ->
            if (isConnected) {
                binding.rvPlaylists.visible = true
                binding.clNotInet.visible = false
                showToast(getString(R.string.connected))
            } else {
                binding.rvPlaylists.visible = false
                binding.clNotInet.visible = true
                showToast(getString(R.string.disconnected))
            }
        })
    }

    override fun inflateVB(inflater: LayoutInflater): ActivityPlaylistBinding {
        binding = ActivityPlaylistBinding.inflate(inflater)
        return binding
    }
}