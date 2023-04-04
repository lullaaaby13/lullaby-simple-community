package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.article

import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.NotFoundException
import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.article.ArticleRepository
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.article.event.ArticleViewEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
@Service
class ArticleQueryService(
    val articleRepository: ArticleRepository,
    val eventPublisher: ApplicationEventPublisher,
) {
    @Transactional(readOnly = true)
    fun getArticles(): List<ArticleDto> {
        return articleRepository.findAll().map { ArticleDto(it) }
    }

    @Transactional
    fun getArticle(articleId: Long, userId: Long? = null): ArticleDto {
        val article = articleRepository.findById(articleId) ?: throw NotFoundException("존재 하지 않는 게시글 입니다.")

        if (userId != null) {
            val articleViewEvent = ArticleViewEvent(articleId = articleId, userId = userId)
            eventPublisher.publishEvent(articleViewEvent)
        }
        return ArticleDto(article)
    }

}
