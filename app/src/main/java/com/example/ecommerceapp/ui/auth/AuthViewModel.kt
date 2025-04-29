import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.network.ApiClient
import com.example.ecommerceapp.data.api.AuthService
import com.example.ecommerceapp.ui.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val api: AuthService = ApiClient.authService

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token.asStateFlow()

    // Handle email change
    fun onEmailChange(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail)
    }

    // Handle password change
    fun onPasswordChange(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword)
    }

    // Handle confirm password change
    fun onConfirmPasswordChange(newConfirmPassword: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = newConfirmPassword)
    }

    // Toggle password visibility
    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(isPasswordVisible = !_uiState.value.isPasswordVisible)
    }

    // Toggle confirm password visibility
    fun toggleConfirmPasswordVisibility() {
        _uiState.value = _uiState.value.copy(isConfirmPasswordVisible = !_uiState.value.isConfirmPasswordVisible)
    }

    // Validate email and password inputs
    fun validateInputs(): Boolean {
        val state = _uiState.value
        return state.email.isNotBlank() && state.password.length >= 6
    }

    // Reset fields
    fun resetFields() {
        _uiState.value = AuthUiState()
    }

    // Set error message to be displayed in the UI
    fun setErrorMessage(message: String) {
        _uiState.value = _uiState.value.copy(errorMessage = message)
    }

    // Register user
    fun register(onSuccess: () -> Unit, onError: (String) -> Unit) {
        val state = _uiState.value
        if (!validateInputs()) {
            val message = "Invalid input for registration"
            Log.d("AuthViewModel", message)
            onError(message)
            return
        }

        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                Log.d("AuthViewModel", "Sending register request: ${state.email}")

                val response = api.register(
                    RegisterRequest(
                        name = "Anonymous",
                        email = state.email,
                        password = state.password,
                        image_url = null
                    )
                )

                if (response.isSuccessful) {
                    Log.d("AuthViewModel", "Registration successful")
                    onSuccess()
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    Log.d("AuthViewModel", "Registration failed: $error")
                    onError("Register failed: $error")
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Registration error: ${e.message}", e)
                onError("Register error: ${e.message}")
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    // Login user
    fun login(onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        val state = _uiState.value
        if (!validateInputs()) {
            val message = "Invalid input for login"
            Log.d("AuthViewModel", message)
            onError(message)
            return
        }

        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                Log.d("AuthViewModel", "Sending login request for ${state.email}")

                val response = api.login(
                    LoginRequest(
                        email = state.email,
                        password = state.password
                    )
                )

                if (response.isSuccessful) {
                    val token = response.body()?.token ?: ""
                    _token.value = token
                    _isLoggedIn.value = true
                    // Save the token in SharedPreferences for persistence
                    sharedPreferences.edit().putString("auth_token", token).apply()
                    if (token != null) {
                        ApiClient.setAuthToken(token) // <-- update AuthInterceptor
                    }
                    Log.d("AuthViewModel", "Login successful, token: $token")
                    onSuccess(token)
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    Log.d("AuthViewModel", "Login failed: $error")
                    onError("Login failed: $error")
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Login error: ${e.message}", e)
                onError("Login error: ${e.message}")
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    // Logout user
    fun logout(onSuccess: () -> Unit = {}, onError: (String) -> Unit = {}) {
        val authToken = _token.value
        if (authToken.isNullOrEmpty()) {
            Log.d("AuthViewModel", "No token found. Skipping logout.")
            _isLoggedIn.value = false
            _token.value = null
            resetFields()
            onSuccess()
            return
        }

        viewModelScope.launch {
            try {
                Log.d("AuthViewModel", "Sending logout request...")
                val response = api.logout("Bearer $authToken")

                if (response.isSuccessful) {
                    Log.d("AuthViewModel", "Logout successful")
                    // Clear the token from SharedPreferences and reset state
                    sharedPreferences.edit().remove("auth_token").apply()
                    _token.value = null
                    _isLoggedIn.value = false
                    resetFields()
                    onSuccess()
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    Log.d("AuthViewModel", "Logout failed: $error")
                    onError("Logout failed: $error")
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Logout error: ${e.message}", e)
                onError("Logout error: ${e.message}")
            }
        }
    }

    // Check login state from SharedPreferences
    fun checkLoginState() {
        val token = sharedPreferences.getString("auth_token", null)
        if (token != null) {
            _token.value = token
            _isLoggedIn.value = true
            Log.d("AuthViewModel", "User is logged in with token: $token")
        } else {
            _isLoggedIn.value = false
            _token.value = null
            Log.d("AuthViewModel", "User is not logged in.")
        }
    }

}

