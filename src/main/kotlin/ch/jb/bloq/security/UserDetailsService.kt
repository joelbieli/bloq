package ch.jb.bloq.security

import ch.jb.bloq.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService: UserDetailsService {

    @Autowired
    private lateinit var userService: UserService

    override fun loadUserByUsername(username: String): UserDetails = UserPrincipal(userService.findUserByUsername(username))

    fun loadUserById(id: Long): UserDetails = UserPrincipal(userService.findUserByUserId(id))
}