package kz.iitu.zakaz_s_soboi.presentation.pages.main.restaurant_page.rv

import androidx.recyclerview.widget.RecyclerView
import kz.iitu.zakaz_s_soboi.databinding.CategoryItemBinding
import kz.iitu.zakaz_s_soboi.presentation.pages.main.restaurant_page.rv.model.MenuItem

class CategoryViewHolder(binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val nameTextView = binding.root
    fun bind(item: MenuItem) {
        nameTextView.text = item.categoryName
    }
}