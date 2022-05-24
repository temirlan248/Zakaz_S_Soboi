package kz.iitu.zakaz_s_soboi.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kz.iitu.zakaz_s_soboi.data.local_storage.dao.CartDao
import kz.iitu.zakaz_s_soboi.data.local_storage.dao.CartItemDao
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.CartEntity
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.CartItemEntity
import kz.iitu.zakaz_s_soboi.data.mapper.toCart
import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.CartRepository
import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.forms.InsertCartForm
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao,
    private val cartItemDao: CartItemDao
) : CartRepository {

    override suspend fun getById(cartId: Int) =
        flow {
            val fetchedCartList = cartDao.findById(cartId) ?: throw Exception("Cart not found")
            val cartList = fetchedCartList.toCart()
            emit(cartList)
        }.flowOn(Dispatchers.IO)

    override suspend fun getAllByUserId(userId: Int) =
        flow {
            val fetchedCartList = cartDao.findByUserId(userId)
            val cartList = fetchedCartList.map { it.toCart() }
            emit(cartList)
        }.flowOn(Dispatchers.IO)

    override suspend fun insert(insertCartForm: InsertCartForm) =
        flow {
            val cartEntity = CartEntity(
                userId = insertCartForm.userId,
                restaurantId = insertCartForm.restaurantId
            )
            val cartId = cartDao.insert(cartEntity)

            insertCartForm.insertCartItemList.forEach { cartItem ->
                val cartItemEntity = CartItemEntity(
                    productId = cartItem.productId,
                    count = cartItem.count,
                    cart_id = cartId.toInt()
                )
                cartItemDao.insert(cartItemEntity)
            }
            emit(Unit)
        }.flowOn(Dispatchers.IO)
}