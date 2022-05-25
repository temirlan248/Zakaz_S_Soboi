package kz.iitu.zakaz_s_soboi.presentation.pages.main.cart_page

import kz.iitu.zakaz_s_soboi.domain.model.Cart

sealed class CartState {
    object Empty : CartState()
    object Loading : CartState()
    data class Error(val message: String) : CartState()
    data class Success(val cart: Cart) : CartState()
    object Uploaded: CartState()
}