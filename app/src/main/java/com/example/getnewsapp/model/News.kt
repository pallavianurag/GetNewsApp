package com.example.getnewsapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

import java.io.Serializable
@Parcelize
@Entity
 data class News  (
                @PrimaryKey
                val title: String,
                 val author: String,
                 val date: String,
                 val imageUrl: String,
                val description:String
): Parcelable