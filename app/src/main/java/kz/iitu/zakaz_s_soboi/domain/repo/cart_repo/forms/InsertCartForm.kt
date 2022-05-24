package kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.forms

data class InsertCartForm(
    val userId: Int,
    val insertCartItemList: List<InsertCartItemForm>,
    val restaurantId: Int,
)

