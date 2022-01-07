package com.example.youtubeapi.ui.video

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.databinding.ItemVideoBinding
import com.example.youtubeapi.extensions.glide
import com.example.youtubeapi.models.Items

class VideoAdapter(private val list: MutableList<Items>) :
    RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    private lateinit var onClick: OnClick

    fun setOnClick(onClick: OnClick) {
        this.onClick = onClick;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAdapter.ViewHolder {
        return ViewHolder(
            ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: VideoAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private var binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(items: Items) {
            binding.ivVideo.glide(items.snippet.thumbnails.default.url)
            binding.tvNameVideo.text = items.snippet.title
            itemView.setOnClickListener {
                onClick.onClick(items)
            }
        }

    }

    interface OnClick {
        fun onClick(items: Items)
    }
}