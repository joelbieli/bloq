package ch.jb.bloq.dtos

class BlogPostDTO(
        var id: Long? = null,
        var title: String,
        var text: String,
        var author: UserDTO? = null,
        var comments: MutableList<CommentDTO> = mutableListOf(),
        var tags: MutableList<TagDTO> = mutableListOf()
) {
    constructor(): this(
            title = "",
            text = ""
    )
}