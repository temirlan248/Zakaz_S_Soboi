package kz.iitu.zakaz_s_soboi.domain.repo.product_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.product_repo.ProductRepository
import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.RestaurantRepository
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke() =
        repository.getAll()
}
