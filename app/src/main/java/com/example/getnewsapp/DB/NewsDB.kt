package com.example.getnewsapp.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.getnewsapp.DB.DAO
import com.example.getnewsapp.model.News


@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class NewsDB: RoomDatabase() {
    abstract fun articlesDao(): DAO
}