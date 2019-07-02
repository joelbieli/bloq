package ch.jb.bloq.controller

import ch.jb.bloq.dtos.BlogPostDTO
import ch.jb.bloq.dtos.CommentDTO
import ch.jb.bloq.mappers.BlogPostMapper
import ch.jb.bloq.mappers.CommentMapper
import ch.jb.bloq.security.CurrentUser
import ch.jb.bloq.security.UserPrincipal
import ch.jb.bloq.services.BlogPostService
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

@Api(value = "Blog Post Management System", description = "Operations pertaining to blog posts")
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

    @ApiOperation(value = "Retrieve all blog posts", responseContainer = "List", response = BlogPostDTO::class)
    @GetMapping("/blogposts")
    fun getAllBlogPosts(): ResponseEntity<List<BlogPostDTO>> =
            ResponseEntity(blogPostMapper.toDTOs(blogPostService.findAll()), HttpStatus.OK)

    @ApiOperation(value = "Retrieve all blog posts with a given tag", responseContainer = "List", response = BlogPostDTO::class)
    @GetMapping("/blogposts/{tag}")
    fun getAllBlogPostsByTag(
            @ApiParam(value = "Tag to filter the blog posts by", required = true)
            @PathVariable
            tag: String
    ): ResponseEntity<List<BlogPostDTO>> =
            ResponseEntity(blogPostMapper.toDTOs(blogPostService.findByTagsName(tag)), HttpStatus.OK)

    @ApiOperation(value = "Retrieve the blog post with the given id", response = BlogPostDTO::class)
    @GetMapping("/blogpost/{id}")
    fun getBlogPostById(
            @ApiParam(value = "Id of the blog post to get", required = true)
            @PathVariable
            id: Long
    ): ResponseEntity<BlogPostDTO> =
            ResponseEntity(blogPostMapper.toDTO(blogPostService.findById(id)), HttpStatus.OK)

    @ApiOperation(value = "Create a new blog post", response = BlogPostDTO::class)
    @PostMapping("/blogpost")
    @PreAuthorize("hasRole('ADMIN')")
    fun createBlogPost(
            @ApiParam(value = "Blog post to create", required = true)
            @RequestBody
            blogPostDTO: BlogPostDTO,
            @CurrentUser currentUser: UserPrincipal
    ): ResponseEntity<BlogPostDTO> =
            ResponseEntity(
                    blogPostMapper.toDTO(blogPostService.save(blogPostMapper.toModel(blogPostDTO).apply {
                        author = userService.findById(currentUser.id)
                    })),
                    HttpStatus.OK
            )

    @ApiOperation(value = "Add a comment to the blog post with the given id", response = BlogPostDTO::class)
    @PostMapping("/blogpost/{id}/comment")
    @PreAuthorize("hasRole('USER')")
    fun addComment(
            @ApiParam(value = "Comment to add", required = true)
            @RequestBody
            commentDTO: CommentDTO,
            @ApiParam(value = "Id of the comment to add the comment to", required = true)
            @PathVariable
            id: Long,
            @CurrentUser currentUser: UserPrincipal
    ): ResponseEntity<BlogPostDTO> =
            ResponseEntity(
                    blogPostMapper.toDTO(blogPostService.addComment(id, commentMapper.toModel(commentDTO).apply {
                        author = userService.findById(currentUser.id)
                    })),
                    HttpStatus.OK
            )

    @ApiOperation(value = "Update an existing blog post", response = BlogPostDTO::class)
    @PutMapping("/blogpost")
    @PreAuthorize("hasRole('ADMIN')")
    fun updateBlogPost(
            @ApiParam(value = "Updated blog post", required = true)
            @RequestBody
            blogPostDTO: BlogPostDTO
    ): ResponseEntity<BlogPostDTO> =
            ResponseEntity(blogPostMapper.toDTO(blogPostService.save(blogPostMapper.toModel(blogPostDTO))), HttpStatus.OK)

    @ApiOperation(value = "Delete the blog post with the given id")
    @DeleteMapping("/blogpost/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteBlogPost(
            @ApiParam(value = "Id of the blog post to delete", required = true)
            @PathVariable
            id: Long
    ): ResponseEntity<*> {
        blogPostService.delete(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
}