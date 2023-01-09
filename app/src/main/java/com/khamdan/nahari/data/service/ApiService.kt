package com.khamdan.nahari.data.service

import com.khamdan.nahari.data.response.ScheduleResponse
import io.ktor.client.*
import io.ktor.client.request.*

class ApiService(private val httpClient: HttpClient) {

    suspend fun getUsers(): List<ScheduleResponse> = httpClient.get(GET_SCHEDULES) {
        parameter("date", "2020-05-29")
        parameter("country", "US")
    }
}