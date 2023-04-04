package com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.out.jpa.comment

import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.comment.Comment
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "lullaby_community_comment")
@Entity
data class CommentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    var id: Long? = null,
    var content: String = "",
    var writerId: Long = -1L,
    var articleId: Long = -1L,
    var createdAt: LocalDateTime = LocalDateTime.MIN,
) {
    fun toDomain(): Comment {
        return Comment(
            this.id,
            this.content,
            this.writerId,
            this.articleId,
            this.createdAt,
        )
    }
}