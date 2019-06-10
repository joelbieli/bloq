package ch.jb.bloq.dtos

class AuthenticationResponseDTO(
        val token: String
) {
    val tokenType: String = "Bearer"
}