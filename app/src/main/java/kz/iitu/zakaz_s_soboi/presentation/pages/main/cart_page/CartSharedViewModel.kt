package kz.iitu.zakaz_s_soboi.presentation.pages.main.cart_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kz.iitu.zakaz_s_soboi.data.provider.UserProvider
import kz.iitu.zakaz_s_soboi.domain.model.Cart
import kz.iitu.zakaz_s_soboi.domain.model.CartItem
import kz.iitu.zakaz_s_soboi.domain.model.Product
import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.forms.InsertCartForm
import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.forms.InsertCartItemForm
import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.use_cases.InsertCartUseCase
import kz.iitu.zakaz_s_soboi.domain.repo.restaurant_repo.use_cases.GetRestaurantUseCase
import javax.inject.Inject

@HiltViewModel
class CartSharedViewModel @Inject constructor(
    private val insertCartUseCase: InsertCartUseCase,
    private val userProvider: UserProvider,
    private val getRestaurantUseCase: GetRestaurantUseCase
) : ViewModel() {

    private var cart: Cart? = null

    private val _cartState = MutableStateFlow<CartState>(CartState.Empty)
    val cartState = _cartState.asStateFlow()

    private val _hasItemsState = MutableStateFlow(false)
    val hasItemsState = _hasItemsState.asStateFlow()

    fun insertCart() {
        viewModelScope.launch {
            val insertCartForm = InsertCartForm(
                userId = cart!!.userId,
                restaurantId = cart!!.restaurantId,
                insertCartItemList = cart!!.itemList.map {
                    InsertCartItemForm(
                        productId = it.productId,
                        count = it.count
                    )
                }
            )
            insertCartUseCase(insertCartForm)
                .catch {
                    _cartState.value = CartState.Error(it.message.toString())
                }
                .collectLatest {
                    _cartState.value = CartState.Uploaded
                    clear()
                }
        }
    }

    fun createCart(restaurantId: Int) {
        viewModelScope.launch {
            getRestaurantUseCase(restaurantId)
                .collectLatest {
                    cart = Cart(
                        userId = userProvider.getToken(),
                        restaurantLocation = it.location,
                        restaurantImage = it.imageUrl,
                        restaurantName = it.name,
                        restaurantId = restaurantId,
                        itemList = mutableListOf()
                    )
                }
            updateState()
        }
    }

    fun addToCart(product: Product) {
        val item = cart!!.itemList.find { it.productId == product.id }
        if (item == null) {
            cart!!.itemList.add(
                CartItem(
                    productId = product.id,
                    productPrice = product.price,
                    productName = product.name,
                    count = 1
                )
            )
        } else {
            item.count++
        }

        updateState()
    }

    private fun updateState() {
        _cartState.value = CartState.Success(cart!!)
        _hasItemsState.value = cart!!.itemList.isNotEmpty()
    }

    fun removeFromCart(product: Product) {
        val item = cart!!.itemList.find { it.productId == product.id } ?: return
        if (item.count <= 1) {
            cart!!.itemList.remove(item)
        } else {
            item.count--
        }
        updateState()
    }

    fun clear() {
        cart = null
    }
}