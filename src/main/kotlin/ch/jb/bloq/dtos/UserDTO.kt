package ch.jb.bloq.dtos

class UserDTO(
        var id: Long?,
        var email: String,
        var password: String,
        var username: String,
        var posts: MutableList<BlogPostDTO>
) {
    constructor(): this(
            null,
            "",
            "",
            "",
            mutableListOf()
    )
}