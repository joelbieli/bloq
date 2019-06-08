package ch.jb.bloq.mappers

import ch.jb.bloq.dtos.TagDTO
import ch.jb.bloq.models.Tag
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface TagMapper {
    fun toDTO(user: Tag): TagDTO
    fun toModel(userDTO: TagDTO): Tag
    fun toDTOs(users: List<Tag>): List<TagDTO>
    fun toModels(userDTOs: List<TagDTO>): List<Tag>
}