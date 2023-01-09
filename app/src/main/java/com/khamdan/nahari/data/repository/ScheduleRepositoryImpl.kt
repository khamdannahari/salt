package com.khamdan.nahari.data.repository

import com.khamdan.nahari.data.response.Response
import com.khamdan.nahari.data.response.toScheduleModel
import com.khamdan.nahari.data.service.ApiService
import com.khamdan.nahari.domain.Schedule
import io.ktor.client.features.*

class ScheduleRepositoryImpl(private val apiService: ApiService) : ScheduleRepository {

    override suspend fun getSchedules(): Response<List<Schedule>> {
        return try {
            val getUsers = apiService.getUsers().map { it.toScheduleModel() }
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