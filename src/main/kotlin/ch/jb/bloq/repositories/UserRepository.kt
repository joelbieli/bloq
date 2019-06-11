package ch.jb.bloq.repositories

import ch.jb.bloq.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByUsername(username: String): User
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
}