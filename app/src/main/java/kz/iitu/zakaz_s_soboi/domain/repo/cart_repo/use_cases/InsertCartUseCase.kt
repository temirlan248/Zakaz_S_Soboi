package kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.CartRepository
import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.forms.InsertCartForm
import kz.iitu.zakaz_s_soboi.domain.repo.category_repo.CategoryRepository
import kz.iitu.zakaz_s_soboi.domain.repo.category_repo.forms.InsertCategoryForm
import javax.inject.Inject

class InsertCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(insertCartForm: InsertCartForm) =
        repository.insert(insertCartForm)
}
