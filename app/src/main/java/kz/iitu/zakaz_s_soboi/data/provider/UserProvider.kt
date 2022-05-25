package kz.iitu.zakaz_s_soboi.data.provider

interface UserProvider {
    fun getToken(): Int
    fun saveToken(token: Int?)
}