package kz.iitu.zakaz_s_soboi.presentation.pages.main.restaurant_page

import kz.iitu.zakaz_s_soboi.domain.model.Restaurant

sealed class RestaurantState {
    object Empty: RestaurantState()
    object Loading: RestaurantState()
    data class Error(val message: String): RestaurantState()
    data class Success(val restaurant: Restaurant): RestaurantState()
}