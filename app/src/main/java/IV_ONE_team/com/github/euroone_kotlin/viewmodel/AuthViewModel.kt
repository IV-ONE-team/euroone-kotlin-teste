package IV_ONE_team.com.github.euroone_kotlin.viewmodel

import IV_ONE_team.com.github.euroone_kotlin.model.User
import IV_ONE_team.com.github.euroone_kotlin.repository.AuthRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Estado da tela de login. Uma `sealed class` garante ao compilador que
 * todos os estados serao tratados pela UI (padrao adotado tambem no
 * projeto de referencia crypto-monitor).
 */
sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val user: User) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}

/**
 * ViewModel de autenticacao. Mantem o [currentUser] em memoria e expoe o
 * [uiState] da tela de login.
 */
class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    fun signIn(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = AuthUiState.Error("Informe email e senha para entrar.")
            return
        }
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val user = repository.signIn(email, password)
            if (user == null) {
                _uiState.value = AuthUiState.Error(
                    "Credenciais invalidas. Verifique email e senha."
                )
            } else {
                _currentUser.value = user
                _uiState.value = AuthUiState.Success(user)
            }
        }
    }

    fun signOut() {
        _currentUser.value = null
        _uiState.value = AuthUiState.Idle
    }

    fun clearError() {
        if (_uiState.value is AuthUiState.Error) {
            _uiState.value = AuthUiState.Idle
        }
    }
}
