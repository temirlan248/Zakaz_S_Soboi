package kz.iitu.zakaz_s_soboi.domain.repo.product_repo

import kotlinx.coroutines.flow.Flow
import kz.iitu.zakaz_s_soboi.domain.repo.product_repo.forms.InsertProductForm

interface ProductRepository {
    suspend fun insert(insertProductForm: InsertProductForm): Flow<Unit>
}