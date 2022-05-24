package kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.RestaurantRepository
import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.form.UpdateRestaurantForm
import javax.inject.Inject

class UpdateRestaurantUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(updateRestaurantForm: UpdateRestaurantForm) =
        repository.update(updateRestaurantForm)
}
