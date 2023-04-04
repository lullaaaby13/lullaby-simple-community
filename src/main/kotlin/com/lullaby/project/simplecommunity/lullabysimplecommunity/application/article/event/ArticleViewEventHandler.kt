package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.article.event

import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.article.Article
import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.article.ArticleRepository
import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.article.ArticleViewerManager
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class ArticleViewEventHandler(
    val articleViewerManager: ArticleViewerManager,
    val articleRepository: ArticleRepository,
) {
    @Async
    @EventListener(ArticleViewEvent::class)
    fun articleViewEventHandler(event: ArticleViewEvent) {

        if (articleViewerManager.canIncreaseViewCount(articleId = event.articleId, userId = event.userId)) {
            articleViewerManager.addViewer(articleId = event.articleId, userId = event.userId)
            val article: Article = articleRepository.findById(articleId = event.articleId)!!
            articleRepository.save(article.increaseViewCount())
        }

    }
}