package kz.iitu.zakaz_s_soboi.presentation.pages.main.history_page.rv.child_rv

import androidx.recyclerview.widget.RecyclerView
import kz.iitu.zakaz_s_soboi.databinding.HistoryCartItemBinding
import kz.iitu.zakaz_s_soboi.domain.model.CartItem

class HistoryCartViewHolder(binding: HistoryCartItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val nameTextView = binding.nameTextView
    private val countTextView = binding.countTextView
    private val priceTextView = binding.priceTextView

    fun bind(item: CartItem) {
        nameTextView.text = item.productName
        countTextView.text = "${item.count} шт"
        priceTextView.text = " за ${item.productPrice} тг"
    }
}