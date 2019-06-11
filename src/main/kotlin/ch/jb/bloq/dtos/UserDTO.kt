package ch.jb.bloq.dtos

class UserDTO(
        var id: Long?,
        var email: String,
        var username: String
) {
    constructor(): this(
            null,
            "",
            ""
    )
}