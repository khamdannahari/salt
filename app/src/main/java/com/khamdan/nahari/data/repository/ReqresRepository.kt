package com.khamdan.nahari.data.repository

import com.khamdan.nahari.data.response.Login
import com.khamdan.nahari.data.response.Response
import com.khamdan.nahari.data.response.Users

interface ReqresRepository {

    suspend fun getUsers(): Response<Users>

    suspend fun login(email: String, password: String): Response<Login>
}