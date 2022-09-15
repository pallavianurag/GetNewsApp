package com.example.getnewsapp.model

import java.io.Serializable

data class NewsApi (
    val status: String,
    val totalRes: String,
    val articles: ArrayList<News>
)