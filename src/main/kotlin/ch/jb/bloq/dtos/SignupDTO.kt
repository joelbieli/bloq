package ch.jb.bloq.dtos

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class SignupDTO(
        @NotBlank @Size(min = 5, max = 25) val username: String,
        @NotBlank @Email @Size(max = 25) val email: String,
        @NotBlank @Email @Size(min = 6) val password: String
)