package kz.iitu.zakaz_s_soboi.presentation.pages.main.restaurant_page.rv

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kz.iitu.zakaz_s_soboi.databinding.ProductItemBinding
import kz.iitu.zakaz_s_soboi.presentation.pages.main.restaurant_page.rv.model.MenuItem

class ProductViewHolder(binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val imageView = binding.productImageView
    private val nameTextView = binding.nameTextView
    private val descriptionTextView = binding.descriptionTextView
    private val priceTextView = binding.priceTextView

    val addImageView = binding.addImageView
    val removeImageView = binding.removeImageView
    private val countTextView = binding.countTextView

    fun bind(item: MenuItem) {
        val product = item.product!!.first
        val count = item.product.second
        Picasso.get()
            .load(product.imageUrl)
            .into(imageView)

        countTextView.text = count.toString()
        priceTextView.text = "${product.price} тг"
        nameTextView.text = product.name
        descriptionTextView.text = product.description
        removeImageView.isVisible = count != 0
    }
}