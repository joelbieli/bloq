package ch.jb.bloq.models

import javax.persistence.*

@Entity
@Table(name = "users")
class User(
        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        @Column
        var email: String,
        @Column
        var password: String,
        @Column
        var username: String,
        @OneToMany(mappedBy = "author")
        var posts: MutableList<BlogPost>
) {
    constructor(): this(
            null,
            "",
            "",
            "",
            mutableListOf()
    )
}