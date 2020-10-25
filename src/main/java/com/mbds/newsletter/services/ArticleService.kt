package com.mbds.newsletter.services

import com.mbds.newsletter.model.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("everything?apiKey=34ff6b016d3740f2bfa3863fde56c1a4")
    fun list(@Query("q") category: String): Call<ArticlesResponse>
}
