package com.example.myapplication_test1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import android.net.Uri
import androidx.core.net.toUri

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import com.example.myapplication_test1.databinding.ItemCharacterLayoutBinding
import com.example.myapplication_test1.databinding.ItemLocationLayoutBinding

class ListAdapter : ListAdapter<ItemData, RecyclerView.ViewHolder>(ItemDiffCallback) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            ItemType.CHARACTER -> VIEW_TYPE_CHARACTER
            ItemType.LOCATION -> VIEW_TYPE_LOCATION
            ItemType.ERROR -> VIEW_TYPE_CHARACTER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CHARACTER -> {
                val binding = ItemCharacterLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CharacterViewHolder(binding)
            }
            VIEW_TYPE_LOCATION -> {
                val binding = ItemLocationLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                LocationViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is CharacterViewHolder -> holder.bind(item)
            is LocationViewHolder -> holder.bind(item)
        }
    }

    inner class CharacterViewHolder(
        private val binding: ItemCharacterLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemData) {
            binding.itemTitle.text = item.title
            binding.itemSubtitle.text = item.subtitle

            if (!item.imageUrl.isNullOrEmpty()) {
                binding.itemImage.load(item.imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_cloud_upload)
                    error(R.drawable.ic_cloud_upload)
                }
            } else {
                binding.itemImage.setImageResource(R.drawable.ic_cloud_upload)
            }
        }
    }

    inner class LocationViewHolder(
        private val binding: ItemLocationLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemData) {
            binding.locationTitle.text = item.title

            val parts = item.subtitle.split(" - ")
            if (parts.size >= 2) {
                binding.locationType.text = "Тип: ${parts[0]}"
                binding.locationDimension.text = "Измерение: ${parts[1]}"
            } else {
                binding.locationType.text = item.subtitle
                binding.locationDimension.text = ""
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_CHARACTER = 0
        private const val VIEW_TYPE_LOCATION = 1

        private object ItemDiffCallback : DiffUtil.ItemCallback<ItemData>() {
            override fun areItemsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
                return oldItem.title == newItem.title && oldItem.type == newItem.type
            }

            override fun areContentsTheSame(oldItem: ItemData, newItem: ItemData): Boolean {
                return oldItem == newItem
            }
        }
    }
}