package kz.iitu.zakaz_s_soboi.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kz.iitu.zakaz_s_soboi.data.local_storage.dao.CategoryDao
import kz.iitu.zakaz_s_soboi.data.local_storage.entity.CategoryEntity
import kz.iitu.zakaz_s_soboi.domain.repo.category_repo.CategoryRepository
import kz.iitu.zakaz_s_soboi.domain.repo.category_repo.forms.InsertCategoryForm
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
): CategoryRepository {
    override suspend fun insert(insertCategoryForm: InsertCategoryForm) =
        flow {
            val categoryEntity = CategoryEntity(
                name = insertCategoryForm.name,
                restaurantId = insertCategoryForm.restaurantId
            )
            categoryDao.insert(categoryEntity)
            emit(Unit)
        }.flowOn(Dispatchers.IO)
}
