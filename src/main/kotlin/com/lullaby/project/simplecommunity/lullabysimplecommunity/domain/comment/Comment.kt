package com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.comment

import java.time.LocalDateTime

data class Comment(
    val id: Long? = null,
    val content: String,
    val writerId: Long,
    val articleId: Long,
    val createdAt: LocalDateTime,
) {

    fun update(content: String): Comment {
        return Comment(
            this.id,
            content,
            this.writerId,
            this.articleId,
            this.createdAt
        )
    }
}