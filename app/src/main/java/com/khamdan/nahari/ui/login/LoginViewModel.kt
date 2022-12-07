package com.khamdan.nahari.ui.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khamdan.nahari.data.repository.ReqresRepositoryImpl
import com.khamdan.nahari.data.response.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val marketRepository: ReqresRepositoryImpl) : ViewModel() {

    private val _state = MutableStateFlow(LoginViewState())
    val state: StateFlow<LoginViewState>
        get() = _state

    fun setEmail(email: String) = _state.update {
        it.copy(email = email)
    }

    fun setPassword(password: String) = _state.update {
        it.copy(password = password)
    }

    fun doneNavigate() = _state.update {
        it.copy(navigate = false)
    }

    fun login() {

        if (_state.value.email != "eve.holt@reqres.in" ||
            _state.value.password != "cityslicka" //no validation from server
        ) {
            _state.update {
                it.copy(message = "Wrong Email or Password")
            }
            return
        }

        _state.update {
            it.copy(loading = true, message = "")
        }
        viewModelScope.launch {
            val users = marketRepository.login(_state.value.email, _state.value.password)
            _state.update {
                when (users) {
                    is Response.Success -> it.copy(loading = false, message = "", navigate = true)
                    else -> it.copy(message = "Wrong Email or Password")
                }
            }
        }
    }
}

data class LoginViewState(
    val loading: Boolean = false,
    val navigate: Boolean = false,
    val email: String = "",
    val password: String = "",
    val message: String = "",
)