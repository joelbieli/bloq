package ch.jb.bloq.dtos

class TagDTO(
        var id: Long?,
        var name: String
) {
    constructor(): this(
            null,
            ""
    )
}