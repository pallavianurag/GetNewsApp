package com.example.getnewsapp.model

import java.io.Serializable

data class NewsApi : Serializable (
    val status: String,
    val totalRes: String,
    val articles: ArrayList<News>
)