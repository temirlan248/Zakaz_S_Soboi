package kz.iitu.zakaz_s_soboi.domain.repo.category_repo

import kz.iitu.zakaz_s_soboi.domain.model.Category
import kz.iitu.zakaz_s_soboi.domain.repo.category_repo.forms.InsertCategoryForm
import kz.iitu.zakaz_s_soboi.domain.repo.category_repo.forms.UpdateCategoryForm

interface CategoryRepository {
    suspend fun getAll(): List<Category>

    suspend fun getById(categoryId: Int): Category?

    suspend fun insert(insertCategoryForm: InsertCategoryForm)

    suspend fun delete(categoryId: Int)

    suspend fun deleteAll()

    suspend fun update(updateCategoryForm: UpdateCategoryForm)
}
