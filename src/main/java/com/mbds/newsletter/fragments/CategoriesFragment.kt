package com.mbds.newsletter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.newsletter.MainActivity
import com.mbds.newsletter.R
import com.mbds.newsletter.adapters.CallBack
import com.mbds.newsletter.adapters.CategoriesAdapter
import com.mbds.newsletter.model.Category

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment(), CallBack {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        val categories = listOf<Category>(
            Category("Politique",
                "https://business-cool.com/wp-content/uploads/2018/11/politique.jpeg"),
            Category("Economie",
                "https://www.tresor.economie.gouv.fr/Articles/9f6a8745-4485-48e7-9ce5-461f144ec67b/images/a4c3e6df-1e50-436c-b974-934b25cb4144"),
            Category("Education",
                "https://www.touteleurope.eu/fileadmin/_TLEv3/education/politique-euro-formation-education.jpg"),
            Category("Pandémie",
                "https://www.actualites.uqam.ca/sites/default/files/styles/grande/public/gettyimages-1201545442_w.jpg?itok=p91YxI6r&c=15612f7d7fed09dac2bb0a70612bbfd9"),
            Category("Sciences",
                "https://www.infomediaire.net/wp-content/uploads/2018/10/Sciences.jpg"),
            Category("Ecologie",
                "https://cdn.futura-sciences.com/buildsv6/images/wide1920/f/b/f/fbf1ffdbee_50145424_ecologie-science.jpg")
        )

        val adapterRecycler = CategoriesAdapter(categories, this)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapterRecycler

    }

    override fun onClick(myCategorie: String) {
        (activity as? MainActivity)?.changeFragment(ArticleFragment.newInstance(myCategorie))
    }
}