package ch.jb.bloq.dtos

import javax.validation.constraints.NotBlank

class LoginDTO(
        @NotBlank val usernameOrEmail: String,
        @NotBlank val password: String
)