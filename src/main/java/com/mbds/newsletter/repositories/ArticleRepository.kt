package com.mbds.newsletter.repositories

import com.mbds.newsletter.services.ArticleService
import com.mbds.newsletter.model.Article
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleRepository {
    private val service: ArticleService
    init {
        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://newsapi.org/v2/")
        }
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ArticleService::class.java)
    }

    fun list(category: String): List<Article> {
        val response = service.list(category).execute()
        return response.body()?.articles?: emptyList()
    }
}
