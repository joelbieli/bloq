package ch.jb.bloq.services

import ch.jb.bloq.exceptions.ResourceNotFoundException
import ch.jb.bloq.models.BlogPost
import ch.jb.bloq.repositories.BlogPostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BlogPostService {

     @Autowired
     private lateinit var blogPostRepository: BlogPostRepository

    fun findAllBlogPosts(): List<BlogPost> = blogPostRepository.findAll()

    fun findById(id: Long): BlogPost {
        val blogPost = blogPostRepository.findById(id)

        if (blogPost.isPresent) {
            return blogPost.get()
        } else {
            throw ResourceNotFoundException("Blog post", "id", id)
        }
    }

    fun save(blogPost: BlogPost): BlogPost = blogPostRepository.save(blogPost)

    fun delete(id: Long) {
        blogPostRepository.deleteById(id)
    }
}