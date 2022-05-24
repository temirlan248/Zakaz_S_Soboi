package kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.RestaurantRepository
import javax.inject.Inject

class DeleteRestaurantUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(restaurantId: Int) =
        repository.delete(restaurantId)
}
