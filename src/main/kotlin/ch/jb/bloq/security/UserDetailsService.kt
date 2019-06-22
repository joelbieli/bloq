package ch.jb.bloq.security

import ch.jb.bloq.exceptions.ResourceNotFoundException
import ch.jb.bloq.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService: UserDetailsService {

    @Autowired
    private lateinit var userService: UserService

    override fun loadUserByUsername(usernameOrEmail: String): UserDetails {
        return when {
            userService.existsByUsername(usernameOrEmail) -> UserPrincipal(userService.findByUsername(usernameOrEmail))
            userService.existsByEmail(usernameOrEmail) -> UserPrincipal(userService.findByEmail(usernameOrEmail))
            else -> throw ResourceNotFoundException("User", "username or email", usernameOrEmail)
        }
    }

    fun loadUserById(id: Long): UserDetails = UserPrincipal(userService.findById(id))
}