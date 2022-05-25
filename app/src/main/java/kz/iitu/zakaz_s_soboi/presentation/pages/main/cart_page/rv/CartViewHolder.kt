package kz.iitu.zakaz_s_soboi.presentation.pages.main.cart_page.rv

import androidx.recyclerview.widget.RecyclerView
import kz.iitu.zakaz_s_soboi.databinding.CartItemBinding
import kz.iitu.zakaz_s_soboi.domain.model.CartItem

class CartViewHolder (binding: CartItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val nameTextView = binding.nameTextView
    private val countTextView = binding.countTextView
    private val priceTextView = binding.priceTextView

    fun bind(item: CartItem) {
        nameTextView.text = item.productName
        countTextView.text ="${item.count} шт "
        priceTextView.text = " за ${item.productPrice} тг"
    }
}