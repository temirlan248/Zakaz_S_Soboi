package kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo

data class InsertRestaurantForm(
    val name: String,
    val location: String,
    val imageUrl: String,
)