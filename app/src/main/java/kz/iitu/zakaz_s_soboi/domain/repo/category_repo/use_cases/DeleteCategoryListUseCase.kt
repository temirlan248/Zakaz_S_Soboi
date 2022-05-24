package kz.iitu.zakaz_s_soboi.domain.repo.category_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.category_repo.CategoryRepository
import javax.inject.Inject

class DeleteCategoryListUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke() =
        repository.deleteAll()
}