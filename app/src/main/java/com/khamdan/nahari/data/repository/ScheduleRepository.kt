package com.khamdan.nahari.data.repository

import com.khamdan.nahari.data.response.Response
import com.khamdan.nahari.domain.Schedule

interface ScheduleRepository {

    suspend fun getSchedules(): Response<List<Schedule>>

}