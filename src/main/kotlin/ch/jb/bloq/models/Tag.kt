package ch.jb.bloq.models

import javax.persistence.*

@Entity
@Table(name = "tags")
class Tag(
        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        @Column
        var name: String
) {
    constructor(): this(
            null,
            ""
    )
}