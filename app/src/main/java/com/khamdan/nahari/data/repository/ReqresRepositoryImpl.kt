package com.khamdan.nahari.data.repository

import com.khamdan.nahari.data.response.Login
import com.khamdan.nahari.data.service.ApiService
import com.khamdan.nahari.data.response.Response
import com.khamdan.nahari.data.response.Users
import io.ktor.client.features.*

class ReqresRepositoryImpl(private val apiService: ApiService) : ReqresRepository {

    override suspend fun getUsers(): Response<Users> {
        return try {
            val getUsers = apiService.getUsers()
            Response.Success(getUsers)
        } catch (e: ClientRequestException) {
            // 4xx
            Response.Error("errorMsg= ${e.response.status.description}")
        } catch (e: ServerResponseException) {
            // 5xx
            Response.Error("errorMsg= ${e.response.status.description}")
        }
    }

    override suspend fun login(email: String, password: String): Response<Login> {
        return try {
            val getUsers = apiService.login(email, password)
            Response.Success(getUsers)
        } catch (e: ClientRequestException) {
            // 4xx
            Response.Error("errorMsg= ${e.response.status.description}")
        } catch (e: ServerResponseException) {
            // 5xx
            Response.Error("errorMsg= ${e.response.status.description}")
        }
    }

}