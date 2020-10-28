package com.mbds.newsletter.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mbds.newsletter.R
import com.mbds.newsletter.model.Category

class CategoriesAdapter(private val dataset: List<Category>, private val callback: CallBack) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    inner class ViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Category) {
            val txtTitle = root.findViewById<TextView>(R.id.title)
            val img = root.findViewById<AppCompatImageView>(R.id.image)
            txtTitle.text = item.name
            Glide.with(root).load(item.image).into(img)
            root.setOnClickListener {
                callback.onClick(item.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount(): Int = dataset.size
}
    interface CallBack {
        fun onClick(categoryName: String)
    }