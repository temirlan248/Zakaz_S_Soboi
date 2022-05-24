package kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.CartRepository
import javax.inject.Inject

class DeleteCartListUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke() =
        repository.deleteAll()
}
