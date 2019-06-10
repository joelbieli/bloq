package ch.jb.bloq.controller

import ch.jb.bloq.dtos.BlogPostDTO
import ch.jb.bloq.mappers.BlogPostMapper
import ch.jb.bloq.models.BlogPost
import ch.jb.bloq.services.BlogPostService
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
    private lateinit var blogPostMapper: BlogPostMapper

    @GetMapping("/blogposts")
    fun getAllBlogPosts(): ResponseEntity<List<BlogPost>> =
            ResponseEntity(blogPostService.findAllBlogPosts(), HttpStatus.OK)

    @GetMapping("/blogpost/{id}")
    fun getBlogPostById(@PathVariable id: Long): ResponseEntity<BlogPost> =
            ResponseEntity(blogPostService.findById(id), HttpStatus.OK)

    @PostMapping("/blogpost")
    @PreAuthorize("hasRole('ADMIN')")
    fun createBlogPost(@RequestBody blogPostDTO: BlogPostDTO): ResponseEntity<BlogPost> =
            ResponseEntity(blogPostService.save(blogPostMapper.toModel(blogPostDTO)), HttpStatus.OK)

    @PutMapping("/blogpost")
    @PreAuthorize("hasRole('ADMIN')")
    fun updateBlogPost(@RequestBody blogPostDTO: BlogPostDTO): ResponseEntity<BlogPost> =
            ResponseEntity(blogPostService.save(blogPostMapper.toModel(blogPostDTO)), HttpStatus.OK)

    @DeleteMapping("/blogpost/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteBlogPost(@PathVariable id: Long): ResponseEntity<*> {
        blogPostService.delete(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
}