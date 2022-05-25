package kz.iitu.zakaz_s_soboi.presentation.pages.main.home_page.rv

import androidx.recyclerview.widget.DiffUtil
import kz.iitu.zakaz_s_soboi.domain.model.Restaurant

class RestaurantDiffCallback : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem == newItem
    }

}