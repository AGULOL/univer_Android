package com.example.myapplication_test1


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ListAdapter(private val listItems: List<ItemData>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]
        holder.titleTextView.text = item.title
        holder.subtitleTextView.text = item.subtitle
        holder.iconImageView.setImageResource(item.iconResId)
    }

    override fun getItemCount(): Int = listItems.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImageView: ImageView = itemView.findViewById(R.id.item_icon)
        val titleTextView: TextView = itemView.findViewById(R.id.item_title)
        val subtitleTextView: TextView = itemView.findViewById(R.id.item_subtitle)
    }
}
