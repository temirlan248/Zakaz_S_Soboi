package kz.iitu.zakaz_s_soboi.presentation.pages.main.history_page.rv.child_rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.iitu.zakaz_s_soboi.databinding.HistoryCartItemBinding
import kz.iitu.zakaz_s_soboi.domain.model.CartItem

class HistoryCartAdapter : RecyclerView.Adapter<HistoryCartViewHolder>() {

    var cartItems: List<CartItem> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryCartViewHolder {
        val binding = HistoryCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryCartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryCartViewHolder, position: Int) {
        val item = cartItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = cartItems.size
}

