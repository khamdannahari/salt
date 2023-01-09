package com.khamdan.nahari.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khamdan.nahari.data.repository.ScheduleRepositoryImpl
import com.khamdan.nahari.data.response.Response
import com.khamdan.nahari.domain.Schedule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val marketRepository: ScheduleRepositoryImpl) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state

    init {
        viewModelScope.launch {
            val users = marketRepository.getSchedules()
            _state.update {
                when (users) {
                    is Response.Success -> it.copy(schedules = users.data)
                    else -> it.copy(schedules = emptyList())
                }
            }
        }
    }
}

data class HomeViewState(
    val schedules: List<Schedule> = emptyList()
)