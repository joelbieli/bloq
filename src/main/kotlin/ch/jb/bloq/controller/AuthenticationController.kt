package ch.jb.bloq.controller

import ch.jb.bloq.dtos.ApiResponseDTO
import ch.jb.bloq.dtos.AuthenticationResponseDTO
import ch.jb.bloq.dtos.LoginDTO
import ch.jb.bloq.dtos.SignupDTO
import ch.jb.bloq.models.User
import ch.jb.bloq.security.JwtProvider
import ch.jb.bloq.security.UserPrincipal
import ch.jb.bloq.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/api/auth")
class AuthenticationController {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @PostMapping("/signin")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginDTO): ResponseEntity<AuthenticationResponseDTO> {
        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
                        loginRequest.usernameOrEmail,
                        loginRequest.password
        ))

        SecurityContextHolder.getContext().authentication = authentication

        val userPrincipal = authentication.principal as UserPrincipal

        return ResponseEntity.ok(AuthenticationResponseDTO(
                JwtProvider.generateToken(authentication),
                userPrincipal.username,
                userPrincipal.authorities.elementAt(0).authority.drop(5)
        ))
    }

    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody signUpRequest: SignupDTO): ResponseEntity<ApiResponseDTO> {
        if (userService.existsByUsername(signUpRequest.username)) {
            return ResponseEntity(ApiResponseDTO(
                    false,
                    "Username is already taken!"
            ), HttpStatus.BAD_REQUEST)
        }

        if (userService.existsByEmail(signUpRequest.email)) {
            return ResponseEntity(ApiResponseDTO(
                    false,
                    "Email Address already in use!"
            ), HttpStatus.BAD_REQUEST)
        }

        val user = User(
                signUpRequest.email,
                signUpRequest.username,
                passwordEncoder.encode(signUpRequest.password)
        )

        userService.save(user)

        return ResponseEntity.ok(ApiResponseDTO(true, "User registered successfully"))
    }
}