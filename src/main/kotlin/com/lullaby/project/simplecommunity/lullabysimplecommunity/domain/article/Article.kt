package com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.article

import java.time.LocalDateTime

data class Article(
    val id: Long? = null,
    val title: String = "",
    val content: String = "",
    val viewCount: Int = 0,
    val createdAt: LocalDateTime = LocalDateTime.MIN,
) {

    fun update(title: String, content: String): Article {
        return Article(
            this.id,
            title,
            content,
            viewCount,
            createdAt
        )
    }

    fun increaseViewCount(): Article {
        return Article(
            this.id,
            this.title,
            this.content,
            this.viewCount + 1,
            this.createdAt
        )
    }

}