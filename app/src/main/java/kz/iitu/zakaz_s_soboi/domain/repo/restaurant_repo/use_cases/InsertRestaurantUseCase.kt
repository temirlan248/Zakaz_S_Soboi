package kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.InsertRestaurantForm
import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.RestaurantRepository
import javax.inject.Inject

class InsertRestaurantUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(insertRestaurantForm: InsertRestaurantForm) =
        repository.insert(insertRestaurantForm)
}
