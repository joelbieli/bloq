package ch.jb.bloq.dtos

class CommentDTO(
        var id: Long?,
        var text: String,
        var author: UserDTO?
)  {
    constructor(): this(
            null,
            "",
            null
    )
}