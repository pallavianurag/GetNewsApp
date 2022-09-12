package com.example.getnewsapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.getnewsapp.adapter.ItemAdapter
import com.example.getnewsapp.databinding.ActivityMainBinding
import com.example.getnewsapp.databinding.ListNewsBinding
import com.example.getnewsapp.data.DataSource
import com.example.getnewsapp.model.News


class MainActivity : AppCompatActivity() {
    //private lateinit var binding: ListNewsBinding
    private lateinit var mAdapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // binding = ListNewsBinding.inflate(layoutInflater) //val view = binding.root
        //setContentView(view)
        /*val myDataset = DataSource().loadNews()
        val recyclerView = findViewById<RecyclerView>(R.id.news_recycler_view)
        recyclerView.adapter = ItemAdapter(this, myDataset)
        recyclerView.setHasFixedSize(true)*/
        val recyclerView = findViewById<RecyclerView>(R.id.news_recycler_view)
        fetchData()
         mAdapter = ItemAdapter(this)
        recyclerView.adapter = mAdapter
    }
    private fun fetchData() {
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=science&piKey=1f4a12d2698e432ea9cf18126dcc7acd"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for(i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            },
            {

            }
        )
        Single.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
        val builder =  CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}

