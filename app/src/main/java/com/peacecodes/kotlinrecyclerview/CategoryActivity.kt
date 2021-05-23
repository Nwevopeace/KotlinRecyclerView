package com.peacecodes.kotlinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.peacecodes.kotlinrecyclerview.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCategory(binding)
    }

    private fun setupCategory(binding: ActivityCategoryBinding) {
        val categories = listOf(
                Category("FA", "Family"),
                Category("BS", "Business"),
                Category("FS", "Friends"),
                Category("CS", "Colleagues"),
                Category("TS", "Tutors")
        )
        val adapter = CategoryAdapter(categories)
        binding.categoryRv.adapter = adapter
        binding.categoryRv.layoutManager = GridLayoutManager(this, 2)
        binding.categoryRv.setHasFixedSize(true)
    }
}