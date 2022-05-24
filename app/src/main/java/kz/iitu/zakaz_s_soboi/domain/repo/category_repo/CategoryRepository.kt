package kz.iitu.zakaz_s_soboi.domain.repo.category_repo

import kotlinx.coroutines.flow.Flow
import kz.iitu.zakaz_s_soboi.domain.repo.category_repo.forms.InsertCategoryForm

interface CategoryRepository {
    suspend fun insert(insertCategoryForm: InsertCategoryForm) : Flow<Unit>
}
