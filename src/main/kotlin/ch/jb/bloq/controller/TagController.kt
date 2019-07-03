package ch.jb.bloq.controller

import ch.jb.bloq.dtos.TagDTO
import ch.jb.bloq.mappers.TagMapper
import ch.jb.bloq.services.TagService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Api(value = "Tag Management System", description = "Operations pertaining to tags")
@Controller
@RequestMapping("/api")
class TagController {

    @Autowired
    private lateinit var tagService: TagService

    @Autowired
    private lateinit var tagMapper: TagMapper

    @ApiOperation(value = "Retrieve all tags", responseContainer = "List", response = TagDTO::class)
    @GetMapping("/tags")
    @PreAuthorize("hasRole('ADMIN')")
    fun getAllTags(): ResponseEntity<List<TagDTO>> =
            ResponseEntity(tagMapper.toDTOs(tagService.findAll()), HttpStatus.OK)

    @ApiOperation(value = "Retrieve the tag with the given id", response = TagDTO::class)
    @GetMapping("/tag/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun getAllTags(
            @ApiParam(value = "Id of the tag to retrieve", required = true)
            @PathVariable
            id: Long
    ): ResponseEntity<TagDTO> =
            ResponseEntity(tagMapper.toDTO(tagService.findById(id)), HttpStatus.OK)

    @ApiOperation(value = "Create a new tag", response = TagDTO::class)
    @PostMapping("/tag")
    @PreAuthorize("hasRole('ADMIN')")
    fun createBlogPost(
            @ApiParam(value = "Tag to create", required = true)
            @RequestBody
            tagDTO: TagDTO
    ): ResponseEntity<TagDTO> =
            ResponseEntity(tagMapper.toDTO(tagService.save(tagMapper.toModel(tagDTO))), HttpStatus.OK)

    @ApiOperation(value = "Update an existing tag", response = TagDTO::class)
    @PutMapping("/tag")
    @PreAuthorize("hasRole('ADMIN')")
    fun updateBlogPost(
            @ApiParam(value = "Updated tag", required = true)
            @RequestBody
            blogPostDTO: TagDTO
    ): ResponseEntity<TagDTO> =
            ResponseEntity(tagMapper.toDTO(tagService.save(tagMapper.toModel(blogPostDTO))), HttpStatus.OK)

    @ApiOperation(value = "Delete the tag with the given id", response = TagDTO::class)
    @DeleteMapping("/tag/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteBlogPost(
            @ApiParam(value = "Id of the tag to delete", required = true)
            @PathVariable
            id: Long
    ): ResponseEntity<*> {
        tagService.delete(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
}