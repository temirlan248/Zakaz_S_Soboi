package kz.iitu.zakaz_s_soboi.domain.repo.category_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.category_repo.CategoryRepository
import kz.iitu.zakaz_s_soboi.domain.repo.category_repo.forms.InsertCategoryForm
import javax.inject.Inject

class InsertCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(insertCategoryForm: InsertCategoryForm) =
        repository.insert(insertCategoryForm)
}
