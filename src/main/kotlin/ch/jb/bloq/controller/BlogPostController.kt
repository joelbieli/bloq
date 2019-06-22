package ch.jb.bloq.controller

import ch.jb.bloq.dtos.BlogPostDTO
import ch.jb.bloq.dtos.CommentDTO
import ch.jb.bloq.mappers.BlogPostMapper
import ch.jb.bloq.mappers.CommentMapper
import ch.jb.bloq.security.CurrentUser
import ch.jb.bloq.security.UserPrincipal
import ch.jb.bloq.services.BlogPostService
import ch.jb.bloq.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api")
class BlogPostController {

    @Autowired
    private lateinit var blogPostService: BlogPostService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var blogPostMapper: BlogPostMapper

    @Autowired
    private lateinit var commentMapper: CommentMapper

    @GetMapping("/blogposts")
    fun getAllBlogPosts(): ResponseEntity<List<BlogPostDTO>> =
            ResponseEntity(blogPostMapper.toDTOs(blogPostService.findAll()), HttpStatus.OK)

    @GetMapping("/blogposts/{tag}")
    fun getAllBlogPostsByTag(@PathVariable tag: String): ResponseEntity<List<BlogPostDTO>> =
            ResponseEntity(blogPostMapper.toDTOs(blogPostService.findByTagsName(tag)), HttpStatus.OK)

    @GetMapping("/blogpost/{id}")
    fun getBlogPostById(@PathVariable id: Long): ResponseEntity<BlogPostDTO> =
            ResponseEntity(blogPostMapper.toDTO(blogPostService.findById(id)), HttpStatus.OK)

    @PostMapping("/blogpost")
    @PreAuthorize("hasRole('ADMIN')")
    fun createBlogPost(
            @RequestBody blogPostDTO: BlogPostDTO,
            @CurrentUser currentUser: UserPrincipal
    ): ResponseEntity<BlogPostDTO> =
            ResponseEntity(
                    blogPostMapper.toDTO(blogPostService.save(blogPostMapper.toModel(blogPostDTO).apply {
                        author = userService.findById(currentUser.id)
                    })),
                    HttpStatus.OK
            )

    @PostMapping("/blogpost/{id}/comment")
    @PreAuthorize("hasRole('USER')")
    fun addComment(
            @RequestBody commentDTO: CommentDTO,
            @PathVariable id: Long,
            @CurrentUser currentUser: UserPrincipal
    ): ResponseEntity<BlogPostDTO> =
            ResponseEntity(
                    blogPostMapper.toDTO(blogPostService.addComment(id, commentMapper.toModel(commentDTO).apply {
                        author = userService.findById(currentUser.id)
                    })),
                    HttpStatus.OK
            )

    @PutMapping("/blogpost")
    @PreAuthorize("hasRole('ADMIN')")
    fun updateBlogPost(@RequestBody blogPostDTO: BlogPostDTO): ResponseEntity<BlogPostDTO> =
            ResponseEntity(blogPostMapper.toDTO(blogPostService.save(blogPostMapper.toModel(blogPostDTO))), HttpStatus.OK)

    @DeleteMapping("/blogpost/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteBlogPost(@PathVariable id: Long): ResponseEntity<*> {
        blogPostService.delete(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
}