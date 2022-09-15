package com.example.getnewsapp.model

import java.io.Serializable

 class News  (
                val title: String,
                 val author: String,
                 val date: String,
                 val imageUrl: String,
) : Serializable