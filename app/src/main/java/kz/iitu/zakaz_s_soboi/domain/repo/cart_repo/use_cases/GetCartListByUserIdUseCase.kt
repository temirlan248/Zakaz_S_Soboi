package kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.CartRepository
import javax.inject.Inject

class GetCartListByUserIdUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(userId: Int) =
        repository.getAllByUserId(userId)
}
