package com.pascal.randommeme.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.pascal.randommeme.R
import com.pascal.randommeme.databinding.ItemListBinding
import com.pascal.randommeme.domain.model.MemesItem

class HomeAdapter(
    val data: List<MemesItem?>?,
    val itemClick: OnClickListener
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val binding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bind(item!!)
    }

    override fun getItemCount(): Int = data!!.size

    inner class ViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MemesItem) {

            binding.title.setText(item.title)
            binding.desc.setText(item.subreddit)
            binding.btnDownload.setOnClickListener {
                itemClick.detail(item)
            }

            Glide.with(binding.root)
                .load(item.url)
                .apply(
                    RequestOptions()
                        .override(1024, 1024)
                        .placeholder(R.drawable.loading)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(binding.imageView)
        }
    }

    interface OnClickListener {
        fun detail(item: MemesItem)
    }
}