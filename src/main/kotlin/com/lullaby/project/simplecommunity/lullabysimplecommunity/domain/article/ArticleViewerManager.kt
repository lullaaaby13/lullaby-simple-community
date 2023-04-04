package com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.article

import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ArticleViewerManager {

    val viewers: MutableMap<ArticleUserKey, LocalDateTime> = mutableMapOf()
    fun canIncreaseViewCount(articleId: Long, userId: Long): Boolean {
        val articleUserKey = ArticleUserKey(articleId = articleId, userId = userId)
        if (!viewers.containsKey(articleUserKey)) {
            return true
        }
        val lastViewedTime: LocalDateTime = viewers[articleUserKey]!!
        return lastViewedTime.isBefore(LocalDateTime.now().minusHours(1))
    }

    fun addViewer(articleId: Long, userId: Long) {
        val articleUserKey = ArticleUserKey(articleId = articleId, userId = userId)
        viewers[articleUserKey] = LocalDateTime.now()
    }
}

data class ArticleUserKey(val articleId: Long, val userId: Long)