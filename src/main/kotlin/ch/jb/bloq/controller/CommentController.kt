package ch.jb.bloq.controller

import ch.jb.bloq.dtos.CommentDTO
import ch.jb.bloq.mappers.CommentMapper
import ch.jb.bloq.security.CurrentUser
import ch.jb.bloq.security.UserPrincipal
import ch.jb.bloq.services.BlogPostService
import ch.jb.bloq.services.CommentService
import ch.jb.bloq.services.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Api(value = "Comment Management System", description = "Operations pertaining to comments")
@Controller
@RequestMapping("/api")
class CommentController {

    @Autowired
    private lateinit var commentService: CommentService

    @Autowired
    private lateinit var commentMapper: CommentMapper

    @ApiOperation(value = "Retrieve all comments", responseContainer = "List", response = CommentDTO::class)
    @GetMapping("/comments")
    fun getAllBlogPosts(): ResponseEntity<List<CommentDTO>> =
            ResponseEntity(commentMapper.toDTOs(commentService.findAll()), HttpStatus.OK)

    @ApiOperation(value = "Retrieve the comment with the given id", response = CommentDTO::class)
    @GetMapping("/comment/{id}")
    fun getBlogPostById(
            @ApiParam(value = "Id of the comment to retrieve", required = true)
            @PathVariable
            id: Long
    ): ResponseEntity<CommentDTO> =
            ResponseEntity(commentMapper.toDTO(commentService.findById(id)), HttpStatus.OK)

    @ApiOperation(value = "Update an existing comment", response = CommentDTO::class)
    @PutMapping("/comment")
    @PreAuthorize("hasRole('USER')")
    fun updateBlogPost(
            @ApiParam(value = "Updated comment", required = true)
            @RequestBody
            commentDTO: CommentDTO
    ): ResponseEntity<CommentDTO> =
            ResponseEntity(commentMapper.toDTO(commentService.save(commentMapper.toModel(commentDTO))), HttpStatus.OK)

    @ApiOperation(value = "Delete the comment with the given id")
    @DeleteMapping("/comment/{id}")
    @PreAuthorize("hasRole('USER')")
    fun deleteBlogPost(
            @ApiParam(value = "Id of the comment to delete", required = true)
            @PathVariable
            id: Long
    ): ResponseEntity<*> {
        commentService.delete(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
}