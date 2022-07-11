package com.picpay.desafio.android.api

import com.picpay.desafio.android.data.model.User
import retrofit2.Response
import retrofit2.http.GET


interface PicPayService {
    companion object {
        val BASE_API_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
        private const val USERS_ENDPOINT = "users"
    }

    @GET(USERS_ENDPOINT)
    suspend fun getUsers(): Response<List<User>>
}