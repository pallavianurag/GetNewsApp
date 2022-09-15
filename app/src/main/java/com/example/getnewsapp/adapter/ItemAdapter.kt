package com.example.getnewsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.getnewsapp.MainActivity
import com.example.getnewsapp.R
import com.example.getnewsapp.model.News

class ItemAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_news, parent, false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]

        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author
        holder.date.text=currentItem.date
    }

    fun updateNews(updatedNews: ArrayList<News>) {
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.myImageView)
    val titleView: TextView = itemView.findViewById(R.id.title)

    val author: TextView = itemView.findViewById(R.id.author)
    val date: TextView=itemView.findViewById(R.id.date)

    //val cardView: CardView= itemView.findViewById(R.id.main_container)
}

interface NewsItemClicked {
    fun onItemClicked(item: News)
}