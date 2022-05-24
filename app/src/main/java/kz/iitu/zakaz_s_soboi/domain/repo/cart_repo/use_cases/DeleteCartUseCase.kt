package kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.CartRepository
import javax.inject.Inject

class DeleteCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(cartId: Int) =
        repository.delete(cartId)
}
