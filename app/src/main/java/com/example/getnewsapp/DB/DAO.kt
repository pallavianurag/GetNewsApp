package com.example.getnewsapp.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.getnewsapp.model.News

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticles(list: List<News>)

    @Query("SELECT * FROM News")
    suspend fun getAllArticles(): List<News>
}