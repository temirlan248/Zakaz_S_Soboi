package kz.iitu.zakaz_s_soboi.domain.repo.product_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.product_repo.ProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: Int) =
        repository.getById(productId)
}
