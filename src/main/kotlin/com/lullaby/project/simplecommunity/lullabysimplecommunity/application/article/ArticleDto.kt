package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.article

import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.article.Article
import java.time.LocalDateTime

data class ArticleDto(
    val id: Long,
    val title: String,
    val content: String,
    val viewCount: Int,
    val createdAt: LocalDateTime,
) {
    constructor(article: Article): this(
        article.id!!,
        article.title,
        article.content,
        article.viewCount,
        article.createdAt
    )
}