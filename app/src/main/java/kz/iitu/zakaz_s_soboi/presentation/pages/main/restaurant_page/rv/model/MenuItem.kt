package kz.iitu.zakaz_s_soboi.presentation.pages.main.restaurant_page.rv.model

import kz.iitu.zakaz_s_soboi.domain.model.Product


data class MenuItem(
    val categoryName: String? = null,
    val product: Pair<Product, Int>? = null
)