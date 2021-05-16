package com.medtech.loginscreenapp.cat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.medtech.loginscreenapp.R

class CatAdapter(
    private val context: Context,
    private val cats: List<CatItem>,
    private val clickListener: ((CatItem, Int) -> Unit)
) :
    RecyclerView.Adapter<CatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val catItem = cats[position]
        holder.itemView.setOnClickListener {
            clickListener.invoke(catItem, position)
        }
        holder.title.text = catItem.title
        Glide.with(context)
            .load(catItem.imageUrl)
            .error(R.drawable.ic_launcher_foreground)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = cats.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.catTitle)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}