package ch.jb.bloq.models

import javax.persistence.*

@Entity
@Table(name = "comments")
class Comment(
        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        @Column
        var text: String,
        @JoinColumn(name = "author_id")
        @ManyToOne
        var author: User?,
        @JoinColumn(name = "post_id")
        @ManyToOne
        var post: BlogPost?
)  {
    constructor(): this(
            null,
            "",
            null,
            null
    )
}