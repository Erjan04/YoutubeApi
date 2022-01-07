package com.example.youtubeapi.ui.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubeapi.databinding.ActivityPlayerBinding
import com.example.youtubeapi.extensions.showToast
import com.example.youtubeapi.utils.`object`.Constant.KEY

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showToast(intent.getStringExtra(KEY).toString())
    }
}