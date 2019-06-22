package ch.jb.bloq.services

import ch.jb.bloq.exceptions.ResourceNotFoundException
import ch.jb.bloq.models.BlogPost
import ch.jb.bloq.models.Comment
import ch.jb.bloq.repositories.BlogPostRepository
import ch.jb.bloq.repositories.TagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BlogPostService {

     @Autowired
     private lateinit var blogPostRepository: BlogPostRepository

    @Autowired
    private lateinit var tagRepository: TagRepository

    fun findAll(): List<BlogPost> = blogPostRepository.findAll()

    fun findByTagsName(tagName: String): List<BlogPost> = blogPostRepository.findByTagsName(tagName)

    fun findById(id: Long): BlogPost {
        val blogPost = blogPostRepository.findById(id)

        if (blogPost.isPresent) {
            return blogPost.get()
        } else {
            throw ResourceNotFoundException("Blog post", "id", id)
        }
    }

    fun addComment(id: Long, comment: Comment): BlogPost = save(findById(id).apply {
        comments.add(comment)
    })

    fun save(blogPost: BlogPost): BlogPost {
        for (tag in blogPost.tags) {
            if (!tagRepository.existsByName(tag.name)) tagRepository.save(tag)
        }

        return blogPostRepository.save(blogPost)
    }

    fun delete(id: Long) {
        blogPostRepository.deleteById(id)
    }
}