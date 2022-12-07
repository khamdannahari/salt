package com.khamdan.nahari.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khamdan.nahari.data.repository.ReqresRepositoryImpl
import com.khamdan.nahari.data.response.Response
import com.khamdan.nahari.data.response.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val marketRepository: ReqresRepositoryImpl) : ViewModel() {

    private val _state = MutableStateFlow(PopularViewState())
    val state: StateFlow<PopularViewState>
        get() = _state

    init {
        viewModelScope.launch {
            val users = marketRepository.getUsers()
            _state.update {
                when (users) {
                    is Response.Success -> it.copy(users = users.data.data.orEmpty())
                    else -> it.copy(users = emptyList())
                }
            }
        }
    }
}

data class PopularViewState(
    val users: List<User> = emptyList()
)