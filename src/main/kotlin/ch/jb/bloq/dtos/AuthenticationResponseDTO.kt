package ch.jb.bloq.dtos

class AuthenticationResponseDTO(
        val token: String,
        val username: String,
        val role: String
) {
    val tokenType: String = "Bearer"
}