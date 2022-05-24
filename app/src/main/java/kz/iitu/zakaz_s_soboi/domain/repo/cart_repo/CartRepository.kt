package kz.iitu.zakaz_s_soboi.domain.repo.cart_repo

import kz.iitu.zakaz_s_soboi.domain.model.Cart
import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.forms.InsertCartForm

interface CartRepository {
    suspend fun getAll() : List<Cart>

    suspend fun getById(cartId: Int): Cart?

    suspend fun getAllByUserId(userId: Int) : List<Cart>

    suspend fun insert(insertCartForm: InsertCartForm)

    suspend fun delete(cartId: Int)

    suspend fun deleteAll()
}