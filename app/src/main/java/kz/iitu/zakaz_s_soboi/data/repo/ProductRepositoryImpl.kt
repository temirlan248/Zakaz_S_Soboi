package kz.iitu.zakaz_s_soboi.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kz.iitu.zakaz_s_soboi.data.local_storage.dao.ProductDao
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.ProductEntity
import kz.iitu.zakaz_s_soboi.domain.repo.product_repo.ProductRepository
import kz.iitu.zakaz_s_soboi.domain.repo.product_repo.forms.InsertProductForm
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductRepository {
    override suspend fun insert(insertProductForm: InsertProductForm) =
        flow {
            val productEntity = ProductEntity(
                imageUrl = insertProductForm.imageUrl,
                name = insertProductForm.name,
                description = insertProductForm.description,
                price = insertProductForm.price,
                categoryId = insertProductForm.categoryId
            )
            productDao.insert(productEntity)
            emit(Unit)
        }.flowOn(Dispatchers.IO)

}
