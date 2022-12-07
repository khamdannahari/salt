package com.khamdan.nahari.data.service

import com.khamdan.nahari.data.response.Login
import com.khamdan.nahari.data.response.Users
import io.ktor.client.*
import io.ktor.client.request.*

class ApiService(private val httpClient: HttpClient) {

    suspend fun getUsers(): Users = httpClient.get(GET_USERS)

    suspend fun login(email: String, password: String): Login = httpClient.get(LOGIN) {
        parameter("email", email)
        parameter("password", password)
    }
}