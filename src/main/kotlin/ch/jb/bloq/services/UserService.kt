package ch.jb.bloq.services

import ch.jb.bloq.exceptions.ResourceNotFoundException
import ch.jb.bloq.models.User
import ch.jb.bloq.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun findByUsername(username: String): User = userRepository.findByUsername(username)

    fun findById(id: Long): User {
        val user = userRepository.findById(id)

        if (user.isPresent) {
            return user.get()
        } else {
            throw ResourceNotFoundException("User", "id", id)
        }
    }

    fun existsByUsername(username: String) = userRepository.existsByUsername(username)

    fun existsByEmail(email: String) = userRepository.existsByEmail(email)

    fun save(user: User) {
        userRepository.save(user)
    }
}