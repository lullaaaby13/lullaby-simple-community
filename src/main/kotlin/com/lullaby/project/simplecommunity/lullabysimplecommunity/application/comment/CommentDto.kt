package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.comment

import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.comment.Comment
import java.time.LocalDateTime

data class CommentDto(
    val id: Long,
    val content: String,
    val writerId: Long,
    val articleId: Long,
    val createdAt: LocalDateTime,
) {
    constructor(comment: Comment): this(
        comment.id!!,
        comment.content,
        comment.writerId,
        comment.articleId,
        comment.createdAt
    )
}