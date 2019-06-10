package ch.jb.bloq

import ch.jb.bloq.models.User
import ch.jb.bloq.repositories.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BloqApplication {
//    @Bean
//    fun init(userRepository: UserRepository) = CommandLineRunner {
//        userRepository.save(User())
//        userRepository.save(User())
//        userRepository.save(User())
//    }
}

fun main(args: Array<String>) {
    runApplication<BloqApplication>(*args)
}