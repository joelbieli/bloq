package ch.jb.bloq.mappers

import ch.jb.bloq.dtos.UserDTO
import ch.jb.bloq.models.User
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UserMapper {
    fun toDTO(user: User): UserDTO
    fun toModel(userDTO: UserDTO): User
    fun toDTOs(users: List<User>): List<UserDTO>
    fun toModels(userDTOs: List<UserDTO>): List<User>
}