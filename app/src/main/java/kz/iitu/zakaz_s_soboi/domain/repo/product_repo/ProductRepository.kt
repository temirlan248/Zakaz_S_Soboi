package kz.iitu.zakaz_s_soboi.domain.repo.product_repo

import kz.iitu.zakaz_s_soboi.domain.model.Product
import kz.iitu.zakaz_s_soboi.domain.repo.product_repo.forms.InsertProductForm
import kz.iitu.zakaz_s_soboi.domain.repo.product_repo.forms.UpdateProductForm

interface ProductRepository {
    suspend fun getAll(): List<Product>

    suspend fun getById(productId: Int): Product?

    suspend fun insert(insertProductForm: InsertProductForm)

    suspend fun delete(productId: Int)

    suspend fun deleteAll()

    suspend fun update(updateProductForm: UpdateProductForm)
}