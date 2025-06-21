package com.example.newsapptask.view.Category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapptask.R
import com.example.newsapptask.databinding.ItemCategoryBinding

class CategoryAdapter(
    val myListenner:(String)->Unit
): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    val categoryList = listOf("business", "technology", "entertainment","sports", "science ","health")

    val categoryLogos: Map<String, Int> = mapOf("business" to R.drawable.business, "technology" to R.drawable.technology,
        "entertainment" to R.drawable.entertainment, "sports" to R.drawable.sports, "science" to R.drawable.science, "health" to R.drawable.health)

    lateinit var binding: ItemCategoryBinding

    class CategoryViewHolder(var binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater : LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding= ItemCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        val logoResId = categoryLogos[category] ?: R.drawable.business
        holder.binding.categoryName.text = category
        holder.binding.categoryImage.setImageResource(logoResId)
        holder.binding.categoryLayout.setOnClickListener {
            myListenner.invoke(category)
        }
    }
}