package ch.jb.bloq.controller

import ch.jb.bloq.dtos.CommentDTO
import ch.jb.bloq.mappers.CommentMapper
import ch.jb.bloq.security.CurrentUser
import ch.jb.bloq.security.UserPrincipal
import ch.jb.bloq.services.BlogPostService
import ch.jb.bloq.services.CommentService
import ch.jb.bloq.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api")
class CommentController {

    @Autowired
    private lateinit var commentService: CommentService

    @Autowired
    private lateinit var commentMapper: CommentMapper

    @GetMapping("/comments")
    fun getAllComments(): ResponseEntity<List<CommentDTO>> =
            ResponseEntity(commentMapper.toDTOs(commentService.findAll()), HttpStatus.OK)

    @GetMapping("/comment/{id}")
    fun getCommentById(@PathVariable id: Long): ResponseEntity<CommentDTO> =
            ResponseEntity(commentMapper.toDTO(commentService.findById(id)), HttpStatus.OK)

    @PutMapping("/comment")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun updateComment(@RequestBody commentDTO: CommentDTO): ResponseEntity<CommentDTO> =
            ResponseEntity(commentMapper.toDTO(commentService.save(commentMapper.toModel(commentDTO))), HttpStatus.OK)

    @DeleteMapping("/comment/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun deleteComment(@PathVariable id: Long): ResponseEntity<*> {
        commentService.delete(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
}