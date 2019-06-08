package ch.jb.bloq.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "logs")
class LogEntry(
        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        @Column
        var dateTime: LocalDateTime,
        @Column
        var description: String
) {
    constructor(): this(
            null,
            LocalDateTime.now(),
            ""
    )

    constructor(description: String): this(
            null,
            LocalDateTime.now(),
            description
    )
}