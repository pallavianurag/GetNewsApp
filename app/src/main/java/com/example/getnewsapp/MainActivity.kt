package com.example.getnewsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.getnewsapp.adapter.ItemAdapter
import com.example.getnewsapp.adapter.NewsItemClicked
import com.example.getnewsapp.model.News


class MainActivity : AppCompatActivity(), NewsItemClicked {
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
        val url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=7ec05041864f4b64bd5d2ffb92afa9ac"
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
                    println(news);
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
        val myIntent:Intent = Intent(this,NewsActivity::class.java)
        startActivity(myIntent)
    }
}

