package ch.jb.bloq.security

import ch.jb.bloq.models.User
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserPrincipal(
        val id: Long,
        private val username: String,
        @JsonIgnore private val email: String,
        @JsonIgnore private val password: String,
        private val authorities: MutableCollection<out GrantedAuthority>
): UserDetails {

    constructor(user: User): this(
            user.id!!,
            user.username,
            user.email,
            user.password,
            mutableListOf(SimpleGrantedAuthority("ROLE_${user.role.name}"))
    )

    override fun getUsername(): String = username

    override fun getPassword(): String = password

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities

    override fun isEnabled(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}