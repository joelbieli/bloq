package ch.jb.bloq.repositories

import ch.jb.bloq.models.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository: JpaRepository<Tag, Long> {
    fun existsByName(name: String): Boolean
    fun findByName(name: String): Tag
}