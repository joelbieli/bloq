package ch.jb.bloq.dtos

import java.time.Instant

class BlogPostDTO(
        var id: Long? = null,
        var title: String,
        var text: String,
        var createdDate: Long = 0,
        var lastEditedDate: Long = 0,
        var author: UserDTO? = null,
        var comments: MutableList<CommentDTO> = mutableListOf(),
        var tags: MutableList<TagDTO> = mutableListOf()
) {
    constructor(): this(
            title = "",
            text = ""
    )
}