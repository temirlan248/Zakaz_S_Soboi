package kz.iitu.zakaz_s_soboi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kz.iitu.zakaz_s_soboi.data.local_storage.dao.CategoryDao
import kz.iitu.zakaz_s_soboi.data.local_storage.dao.ProductDao
import kz.iitu.zakaz_s_soboi.data.local_storage.dao.RestaurantDao
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val restaurantDao: RestaurantDao,
    private val categoryDao: CategoryDao,
    private val productDao: ProductDao
) : ViewModel() {
    fun loadData() {
        viewModelScope.launch {
            if (restaurantDao.getRestaurantList().isNotEmpty()){
                return@launch
            }
            DbInit.restaurantList.forEach { restaurantDao.insert(it) }
            DbInit.categoryList.forEach { categoryDao.insert(it) }
            DbInit.productList.forEach { productDao.insert(it) }
        }
    }
}
