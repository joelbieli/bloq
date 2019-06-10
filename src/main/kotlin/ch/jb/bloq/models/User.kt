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
        var username: String,
        @Column
        var password: String,
        @Column
        var role: Role,
        @OneToMany(mappedBy = "author")
        var posts: MutableList<BlogPost>
) {
    constructor(): this(
            null,
            "",
            "",
            "",
            Role.USER,
            mutableListOf()
    )

    constructor(email: String, username: String, password: String): this(
            null,
            email,
            username,
            password,
            Role.USER,
            mutableListOf()
    )
}