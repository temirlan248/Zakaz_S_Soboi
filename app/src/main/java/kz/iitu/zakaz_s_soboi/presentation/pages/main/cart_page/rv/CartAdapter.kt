package kz.iitu.zakaz_s_soboi.presentation.pages.main.cart_page.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.iitu.zakaz_s_soboi.databinding.CartItemBinding
import kz.iitu.zakaz_s_soboi.domain.model.CartItem

class CartAdapter: RecyclerView.Adapter<CartViewHolder>() {

    var cartItems: List<CartItem> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = cartItems.size
}
