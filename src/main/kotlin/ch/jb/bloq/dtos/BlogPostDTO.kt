package ch.jb.bloq.dtos

class BlogPostDTO(
        var id: Long?,
        var title: String,
        var text: String,
        var author: UserDTO?,
        var comments: MutableList<CommentDTO>,
        var tags: MutableList<TagDTO>
) {
    constructor(): this(
            null,
            "",
            "",
            null,
            mutableListOf(),
            mutableListOf()
    )
}