package kz.iitu.zakaz_s_soboi.domain.repo.category_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.category_repo.CategoryRepository
import kz.iitu.zakaz_s_soboi.domain.repo.category_repo.forms.UpdateCategoryForm
import javax.inject.Inject

class UpdateCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(updateCategoryForm: UpdateCategoryForm) =
        repository.update(updateCategoryForm)
}
