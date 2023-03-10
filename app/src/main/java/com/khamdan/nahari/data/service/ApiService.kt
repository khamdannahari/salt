package com.khamdan.nahari.data.service

import com.khamdan.nahari.data.response.ScheduleResponse
import io.ktor.client.*
import io.ktor.client.request.*

class ApiService(private val httpClient: HttpClient) {

    suspend fun getSchedules(): List<ScheduleResponse> = httpClient.get(GET_SCHEDULES) {
        parameter("date", "2020-05-29")
        parameter("country", "US")
    }

    private companion object {
        const val BASE_URL = "https://api.tvmaze.com/"
        const val GET_SCHEDULES = "$BASE_URL/schedule/web"
    }
}