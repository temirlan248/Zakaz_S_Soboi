package kz.iitu.zakaz_s_soboi.domain.repo.product_repo.use_cases

import kz.iitu.zakaz_s_soboi.domain.repo.product_repo.ProductRepository
import kz.iitu.zakaz_s_soboi.domain.repo.product_repo.forms.UpdateProductForm
import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.form.UpdateRestaurantForm
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(updateProductForm: UpdateProductForm) =
        repository.update(updateProductForm)
}
