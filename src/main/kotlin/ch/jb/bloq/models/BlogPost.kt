package ch.jb.bloq.models

import javax.persistence.*

@Entity
@Table(name = "blog_posts")
class BlogPost(
        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        @Column
        var title: String,
        @Column
        var text: String,
        @JoinColumn(name = "author_id")
        @ManyToOne
        var author: User?,
        @OneToMany(mappedBy = "post")
        var comments: MutableList<Comment>,
        @JoinTable(
                name = "tag_blog_post",
                joinColumns = [JoinColumn(name = "tag_id")],
                inverseJoinColumns = [JoinColumn(name = "blog_post_id")]
        )
        @ManyToMany
        var tags: MutableList<Tag>
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