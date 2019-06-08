package ch.jb.bloq.mappers

import ch.jb.bloq.dtos.BlogPostDTO
import ch.jb.bloq.models.BlogPost
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BlogPostMapper {
    fun toDTO(user: BlogPost): BlogPostDTO
    fun toModel(userDTO: BlogPostDTO): BlogPost
    fun toDTOs(users: List<BlogPost>): List<BlogPostDTO>
    fun toModels(userDTOs: List<BlogPostDTO>): List<BlogPost>
}