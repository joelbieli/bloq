package ch.jb.bloq.models

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "blog_posts")
@EntityListeners(AuditingEntityListener::class)
class BlogPost(
        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        @Column
        var title: String,
        @Column(length = 10485760)
        var text: String,
        @CreatedDate
        @Column(name = "created_date", nullable = false, updatable = false)
        var createdDate: Long,
        @LastModifiedDate
        @Column(name = "last_edited_date")
        var lastEditedDate: Long,
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
            0,
            0,
            null,
            mutableListOf(),
            mutableListOf()
    )
}