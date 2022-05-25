package kz.iitu.zakaz_s_soboi.presentation.pages.main.home_page.state

import kz.iitu.zakaz_s_soboi.domain.model.Restaurant

sealed class RestaurantListState {
    object Empty : RestaurantListState()
    object Loading : RestaurantListState()
    data class Error(val message: String) : RestaurantListState()
    data class Success(val restaurantList: List<Restaurant>) : RestaurantListState()
}
