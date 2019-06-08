package ch.jb.bloq.mappers

import ch.jb.bloq.dtos.CommentDTO
import ch.jb.bloq.models.Comment
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CommentMapper {
    fun toDTO(user: Comment): CommentDTO
    fun toModel(userDTO: CommentDTO): Comment
    fun toDTOs(users: List<Comment>): List<CommentDTO>
    fun toModels(userDTOs: List<CommentDTO>): List<Comment>
}