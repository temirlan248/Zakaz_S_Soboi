package kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.forms

data class InsertCartItemForm(
    val cartId: Int,
    val productId: Int,
    val count: Int,
)