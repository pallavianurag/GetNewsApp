package com.example.getnewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.example.getnewsapp.model.News

class NewsActivity : AppCompatActivity() {
    private lateinit var item: News
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
         item= intent.getSerializableExtra("data") as News

        val image: ImageView = findViewById(R.id.myImageView)
        val titleView: TextView = findViewById(R.id.title)

        val author: TextView = findViewById(R.id.author)
        val date: TextView=findViewById(R.id.date)
        fetchData()
        image.setImageResource()
        titleView.text="x"
            //val cardView: CardView= itemView.findViewById(R.id.main_container)
        }
    }
private fun fetchData() {
    val url =
        "https://newsapi.org/v2/top-headlines?country=in&apiKey=7ec05041864f4b64bd5d2ffb92afa9ac"
    val jsonObjectRequest = object : JsonObjectRequest(
        Request.Method.GET,
        url,
        null,
        {
            val newsJsonArray = it.getJSONArray("articles")
            val newsArray = ArrayList<News>()
            for (i in 0 until newsJsonArray.length()) {
                val newsJsonObject = newsJsonArray.getJSONObject(i)
                val news = News(
                    newsJsonObject.getString("urlToImage"),
                    newsJsonObject.getString("title"),
                    newsJsonObject.getString("author"),
                    newsJsonObject.getString("url")

                )
                println(news);
                newsArray.add(news)
            }

        },
        {

        }
    ) {
        @Throws(AuthFailureError::class)
        override fun getHeaders(): Map<String, String> {
            val params: MutableMap<String, String> = HashMap()
            params["User-Agent"] = "Mozilla/5.0"
            return params
        }
    }
    Single.getInstance(this).addToRequestQueue(jsonObjectRequest)
}

}