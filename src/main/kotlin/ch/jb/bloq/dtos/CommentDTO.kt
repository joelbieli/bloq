package ch.jb.bloq.dtos

class CommentDTO(
        var id: Long?,
        var text: String = "",
        var author: UserDTO? = null
)  {
    constructor(): this(
            null,
            "",
            null
    )
}