package kz.iitu.zakaz_s_soboi.presentation.pages.main.history_page

import kz.iitu.zakaz_s_soboi.domain.model.Cart

sealed class HistoryState {
    object Empty: HistoryState()
    object Exit: HistoryState()
    object Loading: HistoryState()
    data class Error(val message: String): HistoryState()
    data class Success(val cartList:List<Cart>): HistoryState()
}