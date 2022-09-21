package com.example.getnewsapp.DB

import com.example.getnewsapp.model.News
import timber.log.Timber
import javax.inject.Inject


class NewsLocalDataSource @Inject constructor(private val articleDao: DAO) {

    suspend fun getAllArticles(): List<News> {
        return DAO.getAllArticles()
    }

    suspend fun saveAllArticles(articlesList: List<News>) {
        Timber.e("Saving articles ${articlesList.size}")
        DAO.saveArticles(articlesList)
    }

}