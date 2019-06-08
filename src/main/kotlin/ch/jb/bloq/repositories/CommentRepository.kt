package ch.jb.bloq.repositories

import ch.jb.bloq.models.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
}