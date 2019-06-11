package ch.jb.bloq.services

import ch.jb.bloq.exceptions.ResourceNotFoundException
import ch.jb.bloq.models.Tag
import ch.jb.bloq.repositories.TagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TagService {

    @Autowired
    private lateinit var tagRepository: TagRepository

    fun findAll(): List<Tag> = tagRepository.findAll()

    fun findById(id: Long): Tag {
        val tag = tagRepository.findById(id)

        if (tag.isPresent) {
            return tag.get()
        } else {
            throw ResourceNotFoundException("Blog post", "id", id)
        }
    }

    fun save(tag: Tag): Tag = tagRepository.save(tag)

    fun delete(id: Long) = tagRepository.deleteById(id)
}