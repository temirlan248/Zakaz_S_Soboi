package kz.iitu.zakaz_s_soboi.domain.repo.cart_repo

import kotlinx.coroutines.flow.Flow
import kz.iitu.zakaz_s_soboi.domain.model.Cart
import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.forms.InsertCartForm

interface CartRepository {
    suspend fun getById(cartId: Int): Flow<Cart>

    suspend fun getAllByUserId(userId: Int) : Flow<List<Cart>>

    suspend fun insert(insertCartForm: InsertCartForm) : Flow<Unit>

}