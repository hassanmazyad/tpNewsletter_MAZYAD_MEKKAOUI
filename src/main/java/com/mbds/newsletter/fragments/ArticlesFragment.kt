package com.mbds.newsletter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.ArticleAdapter
import com.mbds.newsletter.repositories.ArticleRepository
import com.mbds.newsletter.model.Article
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


/**
 * A simple [Fragment] subclass.
 * Use the [ArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleFragment : Fragment(), CoroutineScope {
    lateinit var recyclerView: RecyclerView
    private lateinit var category: String
    private val repository = ArticleRepository()
    private lateinit var articleData: List<Article>
    private var job: Job = Job()

    companion object {
        fun newInstance(categoryName: String): ArticleFragment {
            return ArticleFragment().apply {
                this.category = categoryName
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch {
            val loaderData = async(Dispatchers.IO) {
                articleData = repository.list(category)
            }
            view.findViewById<TextView>(R.id.category_title).text = category
            recyclerView = view.findViewById(R.id.recycler_view)
            loaderData.await()
            val adapterRecycler = ArticleAdapter(articleData)
            recyclerView.layoutManager = LinearLayoutManager(view.context)
            recyclerView.adapter = adapterRecycler
        }
    }
}