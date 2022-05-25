package kz.iitu.zakaz_s_soboi.presentation.pages.main.home_page.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kz.iitu.zakaz_s_soboi.databinding.RestaurantItemBinding
import kz.iitu.zakaz_s_soboi.domain.model.Restaurant

class RestaurantListAdapter :
    ListAdapter<Restaurant, RestaurantViewHolder>(RestaurantDiffCallback()) {

    var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            RestaurantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item.id)
        }
    }
}

