package com.picpay.desafio.android.data

import com.picpay.desafio.android.api.PicPayService
import com.picpay.desafio.android.data.model.User
import retrofit2.Response

class PicpayRepository(
    private val service: PicPayService
) {

    suspend fun getUsers(): List<User> {
        val response = service.getUsers()
        return if (response.isSuccessful && response.body() != null) {
            response.body()!!
        } else {
            emptyList()
        }
    }
}