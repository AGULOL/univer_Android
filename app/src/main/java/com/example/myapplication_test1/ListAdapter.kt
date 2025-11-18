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

class ListAdapter(private val items: List<ItemData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_CHARACTER = 0
        private const val TYPE_LOCATION = 1
    }

    // ViewHolder для персонажей
    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.item_title)
        val subtitle: TextView = view.findViewById(R.id.item_subtitle)
        val image: ImageView = view.findViewById(R.id.item_image)
    }

    // ViewHolder для локаций
    class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.location_title)
        val type: TextView = view.findViewById(R.id.location_type)
        val dimension: TextView = view.findViewById(R.id.location_dimension)
//        val icon: ImageView = view.findViewById(R.id.location_icon)
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            "character" -> TYPE_CHARACTER
            "location" -> TYPE_LOCATION
            else -> TYPE_CHARACTER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_CHARACTER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_character_layout, parent, false)
                CharacterViewHolder(view)
            }
            TYPE_LOCATION -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_location_layout, parent, false)
                LocationViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_character_layout, parent, false)
                CharacterViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        when (holder) {
            is CharacterViewHolder -> {
                println(item)
                holder.title.text = item.title
                holder.subtitle.text = item.subtitle

                if (!item.imageUrl.isNullOrEmpty())
                {
//                        //crossfade(true)
//                        //placeholder(R.drawable.ic_cloud_upload)
//                        //error(R.drawable.ic_cloud_upload)
//                    }
                    holder.image.setImageURI(item.imageUrl.toUri())
//
                } else {
                    holder.image.setImageResource(R.drawable.ic_cloud_upload)
                }
            }
            is LocationViewHolder -> {
                // Разбиваем subtitle на тип и измерение
                val parts = item.subtitle.split(" - ")
                holder.title.text = item.title
                if (parts.size >= 2) {
                    holder.type.text = "Тип: ${parts[0]}"
                    holder.dimension.text = "Измерение: ${parts[1]}"
                } else {
                    holder.type.text = item.subtitle
                    holder.dimension.text = ""
                }

                // Устанавливаем иконку для локации
//                holder.icon.setImageResource(item.imageRes ?: R.drawable.ic_bank)
            }
        }
    }

    override fun getItemCount() = items.size
}