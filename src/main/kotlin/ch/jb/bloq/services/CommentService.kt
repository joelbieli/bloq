package ch.jb.bloq.services

import ch.jb.bloq.exceptions.ResourceNotFoundException
import ch.jb.bloq.models.Comment
import ch.jb.bloq.repositories.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentService {

    @Autowired
    private lateinit var commentRepository: CommentRepository

    fun findAll(): List<Comment> = commentRepository.findAll()

    fun findById(id: Long): Comment {
        val comment = commentRepository.findById(id)

        if (comment.isPresent) {
            return comment.get()
        } else {
            throw ResourceNotFoundException("Blog post", "id", id)
        }
    }

    fun save(comment: Comment): Comment = commentRepository.save(comment)

    fun delete(id: Long) = commentRepository.deleteById(id)
}