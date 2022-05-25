package kz.iitu.zakaz_s_soboi.presentation.pages.main.history_page.rv

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kz.iitu.zakaz_s_soboi.databinding.HistoryItemBinding
import kz.iitu.zakaz_s_soboi.domain.model.Cart
import kz.iitu.zakaz_s_soboi.presentation.pages.main.history_page.rv.child_rv.HistoryCartAdapter

class HistoryViewHolder(binding: HistoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val restaurantImageView = binding.restaurantImageView
    private val restaurantNameTextView = binding.restaurantNameTextView
    private val restaurantLocationTextView = binding.restaurantLocationImageView
    private val cartRecyclerView = binding.itemsRecyclerView

    fun bind(item: Cart) {
        Picasso.get()
            .load(item.restaurantImage)
            .into(restaurantImageView)

        restaurantNameTextView.text = item.restaurantName
        restaurantLocationTextView.text = item.restaurantLocation
        val historyCartAdapter = HistoryCartAdapter()
        cartRecyclerView.adapter = historyCartAdapter
        historyCartAdapter.cartItems = item.itemList
    }
}