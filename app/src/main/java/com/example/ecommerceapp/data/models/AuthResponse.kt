data class AuthUser(
    val id: Int,
    val name: String,
    val email: String,
    val role: String,
    val image_url: String?
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val role: String? = "customer",
    val image_url: String? = null
)

data class RegisterResponse(
    val message: String,
    val user: AuthUser
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val user: AuthUser
)

data class LogoutResponse(
    val message: String
)
