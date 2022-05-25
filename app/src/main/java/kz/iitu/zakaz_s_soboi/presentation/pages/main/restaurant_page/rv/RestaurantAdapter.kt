package kz.iitu.zakaz_s_soboi.presentation.pages.main.restaurant_page.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.iitu.zakaz_s_soboi.databinding.CategoryItemBinding
import kz.iitu.zakaz_s_soboi.databinding.ProductItemBinding
import kz.iitu.zakaz_s_soboi.domain.model.Product
import kz.iitu.zakaz_s_soboi.presentation.pages.main.restaurant_page.rv.model.MenuItem

class RestaurantAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val CATEGORY = 1
        private const val PRODUCT = 2
    }

    var onAddClickListener: ((Product) -> Unit)? = null
    var onRemoveClickListener: ((Product) -> Unit)? = null

    var menuList: MutableList<MenuItem> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return if (menuList[position].categoryName == null) {
            PRODUCT
        } else {
            CATEGORY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == CATEGORY) {
            val binding =
                CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoryViewHolder(binding)
        }
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var item = menuList[position]
        if (getItemViewType(position) == CATEGORY) {
            (holder as CategoryViewHolder).bind(item)
        } else {
            (holder as ProductViewHolder).bind(item)
            holder.addImageView.setOnClickListener {
                onAddClickListener?.invoke(item.product!!.first)
                item = MenuItem(product = Pair(item.product!!.first, item.product!!.second + 1))
                holder.bind(item)
            }

            holder.removeImageView.setOnClickListener {
                onRemoveClickListener?.invoke(item.product!!.first)
                item = MenuItem(product = Pair(item.product!!.first, item.product!!.second - 1))
                holder.bind(item)
            }
        }
    }

    override fun getItemCount() = menuList.size
}

