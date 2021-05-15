package com.medtech.loginscreenapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter(
    private val itemModels: List<ItemModel>,
    private val clickListener: ((ItemModel) -> Unit)
) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemModels[position]
        holder.itemView.setOnClickListener {
            clickListener.invoke(item)
        }
        holder.name.text = item.name
        holder.description.text = item.description
    }

    override fun getItemCount(): Int {
        return itemModels.size
    }

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val name: TextView = listItemView.findViewById(R.id.name)
        val description: TextView = listItemView.findViewById(R.id.description)
    }
}