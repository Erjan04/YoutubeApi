package com.example.youtubeapi.ui.playlists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.R
import com.example.youtubeapi.databinding.ItemPlaylistBinding
import com.example.youtubeapi.extensions.glide
import com.example.youtubeapi.models.Items
import com.example.youtubeapi.models.PlayList

class PlaylistAdapter(private val list: MutableList<Items>) :
    RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {
    private lateinit var onClick: OnClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistAdapter.ViewHolder {
        return ViewHolder(
            ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlaylistAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun setOnClick(onClick: OnClick) {
        this.onClick = onClick
    }

    inner class ViewHolder(private var binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(playlist: Items) {
            binding.ivPlaylist.glide(playlist.snippet.thumbnails.default.url)
            binding.tvNamePlaylist.text = playlist.snippet.title
            binding.tvCountVideo.text =
                playlist.contentDetails.itemCount.toString() + itemView.context.getString(
                    R.string.video_series
                )
            itemView.setOnClickListener {
                onClick.onClicked(playlist)
            }
        }
    }

    interface OnClick {
        fun onClicked(position: Items)
    }
}
