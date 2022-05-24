package kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.CartRepository
import kz.iitu.zakaz_s_soboi.domain.repo.category_repo.CategoryRepository
import javax.inject.Inject

class GetCartListUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke() =
        repository.getAll()
}
