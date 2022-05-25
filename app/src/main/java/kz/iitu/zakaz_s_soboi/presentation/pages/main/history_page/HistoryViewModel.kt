package kz.iitu.zakaz_s_soboi.presentation.pages.main.history_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kz.iitu.zakaz_s_soboi.data.provider.UserProvider
import kz.iitu.zakaz_s_soboi.domain.repo.cart_repo.use_cases.GetCartListByUserIdUseCase
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val userProvider: UserProvider,
    private val getCartListByUserIdUseCase: GetCartListByUserIdUseCase
) : ViewModel() {

    private var job = Job()
        get() {
            if (field.isCancelled)
                field = Job()
            return field
        }

    private val _historyState = MutableStateFlow<HistoryState>(HistoryState.Empty)
    val historyState = _historyState.asStateFlow()

    init {
        getCartList()
    }

    fun getCartList() {
        val userId = userProvider.getToken()
        viewModelScope.launch(job) {
            getCartListByUserIdUseCase(userId)
                .onStart {
                    _historyState.value = HistoryState.Loading
                }
                .catch {
                    _historyState.value = HistoryState.Error(it.message.toString())
                }
                .collectLatest {
                    _historyState.value = HistoryState.Success(it)
                }
        }
    }

    fun cancel() = job.cancel()

    fun exit() {
        userProvider.saveToken(null)
        _historyState.value = HistoryState.Exit
    }
}