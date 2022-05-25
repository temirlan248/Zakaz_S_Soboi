package kz.iitu.zakaz_s_soboi.presentation.pages.main.restaurant_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kz.iitu.zakaz_s_soboi.domain.model.Restaurant
import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.use_cases.GetRestaurantUseCase
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val getRestaurantUseCase: GetRestaurantUseCase
) : ViewModel() {
    private var job = Job()
        get() {
            if (field.isCancelled) {
                field = Job()
            }
            return field
        }

    private val _restaurantState = MutableStateFlow<RestaurantState>(RestaurantState.Empty)
    val restaurantState = _restaurantState.asStateFlow()

    fun cancel() = job.cancel()

    fun getRestaurant(restaurantId: Int) {
        viewModelScope.launch(job) {
            getRestaurantUseCase(restaurantId)
                .onStart {
                    _restaurantState.value = RestaurantState.Loading
                }
                .catch {
                    _restaurantState.value = RestaurantState.Error(it.message.toString())
                }
                .collectLatest {
                    _restaurantState.value = RestaurantState.Success(it)
                }
        }
    }
}