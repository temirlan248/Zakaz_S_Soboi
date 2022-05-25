package kz.iitu.zakaz_s_soboi.presentation.pages.main.history_page.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.iitu.zakaz_s_soboi.databinding.HistoryItemBinding
import kz.iitu.zakaz_s_soboi.domain.model.Cart

class HistoryAdapter : RecyclerView.Adapter<HistoryViewHolder>() {

    var cartList: List<Cart> = mutableListOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = cartList[position]
        holder.bind(item)
    }

    override fun getItemCount() = cartList.size

}

