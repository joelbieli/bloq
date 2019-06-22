package ch.jb.bloq.controller

import ch.jb.bloq.dtos.TagDTO
import ch.jb.bloq.mappers.TagMapper
import ch.jb.bloq.services.TagService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api")
class TagController {

    @Autowired
    private lateinit var tagService: TagService

    @Autowired
    private lateinit var tagMapper: TagMapper

    @GetMapping("/tags")
    @PreAuthorize("hasRole('ADMIN')")
    fun getAllTags(): ResponseEntity<List<TagDTO>> =
            ResponseEntity(tagMapper.toDTOs(tagService.findAll()), HttpStatus.OK)

    @GetMapping("/tag/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun getAllTags(@PathVariable id: Long): ResponseEntity<TagDTO> =
            ResponseEntity(tagMapper.toDTO(tagService.findById(id)), HttpStatus.OK)

    @PostMapping("/tag")
    @PreAuthorize("hasRole('ADMIN')")
    fun createBlogPost(@RequestBody tagDTO: TagDTO): ResponseEntity<TagDTO> =
            ResponseEntity(tagMapper.toDTO(tagService.save(tagMapper.toModel(tagDTO))), HttpStatus.OK)

    @PutMapping("/tag")
    @PreAuthorize("hasRole('ADMIN')")
    fun updateBlogPost(@RequestBody blogPostDTO: TagDTO): ResponseEntity<TagDTO> =
            ResponseEntity(tagMapper.toDTO(tagService.save(tagMapper.toModel(blogPostDTO))), HttpStatus.OK)

    @DeleteMapping("/tag/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteBlogPost(@PathVariable id: Long): ResponseEntity<*> {
        tagService.delete(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
}