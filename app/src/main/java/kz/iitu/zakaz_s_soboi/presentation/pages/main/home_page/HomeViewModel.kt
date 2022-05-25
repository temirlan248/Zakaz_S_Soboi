package kz.iitu.zakaz_s_soboi.presentation.pages.main.home_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.use_cases.GetRestaurantListUseCase
import kz.iitu.zakaz_s_soboi.presentation.pages.main.home_page.state.RestaurantListState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRestaurantListUseCase: GetRestaurantListUseCase
) : ViewModel() {

    private val _restaurantListState =
        MutableStateFlow<RestaurantListState>(RestaurantListState.Empty)
    val restaurantListState = _restaurantListState.asStateFlow()

    private var job = Job()
        get() {
            if (field.isCancelled)
                field = Job()
            return field
        }

    init {
        getRestaurantList()
    }

    fun getRestaurantList() {
        viewModelScope.launch {
            getRestaurantListUseCase()
                .onStart {
                    _restaurantListState.value = RestaurantListState.Loading
                }
                .catch {
                    _restaurantListState.value = RestaurantListState.Error(it.message.toString())
                }
                .collectLatest {
                    _restaurantListState.value = RestaurantListState.Success(it)
                }
        }
    }

    fun cancel() = job.cancel()
}