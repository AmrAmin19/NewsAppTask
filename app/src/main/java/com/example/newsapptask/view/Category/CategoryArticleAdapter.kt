package com.example.newsapptask.view.Category

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.newsapptask.model.Article
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapptask.databinding.ItemCategoryArticleBinding



class CategoryDiffUtil : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title==newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return  oldItem == newItem
    }

}

//class CategoryArticleAdapter (
//    var myListenner:(Article)->Unit
//) : ListAdapter<Article, CategoryArticleAdapter.CategoryArticleViewHolder>(CategoryDiffUtil()) {
//
//    lateinit var binding: ItemCategoryArticleBinding
//    class CategoryArticleViewHolder(val binding: ItemCategoryArticleBinding) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryArticleViewHolder {
//        Log.d("Amr", "from adapter  ")
//        val inflater : LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        binding = ItemCategoryArticleBinding.inflate(inflater,parent,false)
//        return CategoryArticleViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: CategoryArticleViewHolder, position: Int) {
//        var article = getItem(position)
//        Log.d("Amr", "from adapter  ")
//        holder.binding.newsTitle.text = article.title
//
//        Glide.with(holder.itemView.context)
//            .load(article.image)
//            .into(binding.newsImage)
//
//        holder.binding.itemCategoryArticle.setOnClickListener {
//            myListenner.invoke(article)
//        }
//
//
//    }
//
//}

class CategoryArticleAdapter(
    var myListener: (Article) -> Unit
) : ListAdapter<Article, CategoryArticleAdapter.CategoryArticleViewHolder>(CategoryDiffUtil()) {

    class CategoryArticleViewHolder(val binding: ItemCategoryArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryArticleBinding.inflate(inflater, parent, false)
        return CategoryArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryArticleViewHolder, position: Int) {
        val article = getItem(position)
        Log.d("Amr", "from adapter")

        holder.binding.newsTitle.text = article.title

        Glide.with(holder.itemView.context)
            .load(article.image)
            .into(holder.binding.newsImage)

        holder.binding.itemCategoryArticle.setOnClickListener {
            myListener.invoke(article)
        }
    }
}