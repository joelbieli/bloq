package ch.jb.bloq.repositories

import ch.jb.bloq.models.BlogPost
import org.springframework.data.jpa.repository.JpaRepository

interface BlogPostRepository: JpaRepository<BlogPost, Long> {
    fun findByTagsName(name: String): List<BlogPost>
}