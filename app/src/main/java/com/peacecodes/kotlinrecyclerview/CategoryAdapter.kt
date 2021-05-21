package com.peacecodes.kotlinrecyclerview

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peacecodes.kotlinrecyclerview.databinding.CategoryListItemBinding
import java.util.*

class CategoryAdapter(private val categories: List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bindItems(category, position)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class CategoryViewHolder(private val binding: CategoryListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(category: Category, position: Int) {
            val rnd = Random()
            val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            val androidColors = binding.root.context.resources.getIntArray(R.array.androidColors)

            binding.categoryRl.setBackgroundColor(androidColors[position])
            binding.initialTv.text = category.initial
            binding.categoryTv.text = category.name

            binding.categoryRl.setOnClickListener {
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.putExtra("NAME", category.name)
                binding.root.context.startActivity(intent)
            }
        }
    }
}