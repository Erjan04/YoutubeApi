package com.example.youtubeapi.ui.video

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubeapi.`object`.Constant.KEY
import com.example.youtubeapi.databinding.ActivityVideoBinding
import com.example.youtubeapi.extensions.showToast

class VideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val text = intent.getStringExtra(KEY)
        if (text != null) {
            showToast(text)
        }
    }
}