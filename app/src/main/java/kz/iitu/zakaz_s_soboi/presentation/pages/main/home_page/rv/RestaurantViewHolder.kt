package kz.iitu.zakaz_s_soboi.presentation.pages.main.home_page.rv

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kz.iitu.zakaz_s_soboi.R
import kz.iitu.zakaz_s_soboi.databinding.RestaurantItemBinding
import kz.iitu.zakaz_s_soboi.domain.model.Restaurant

class RestaurantViewHolder(binding: RestaurantItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val imageView = binding.restaurantImageView
    private val nameTextView = binding.nameTextView
    private val addressTextView = binding.addressTextView

    fun bind(item: Restaurant) {
        try {
            Picasso.get()
                .load(item.imageUrl)
                .into(imageView)
        } catch (e: Exception) {
            Picasso.get()
                .load(R.drawable.ic_baseline_food_bank_24)
                .into(imageView)
        }
        nameTextView.text = item.name
        addressTextView.text = item.name
    }
}