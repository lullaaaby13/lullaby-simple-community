package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.article

import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.NotFoundException
import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.article.Article
import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.article.ArticleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@Service
class ArticleCommandService(
    val articleRepository: ArticleRepository,
) {
    fun createArticle(command: CreateArticleCommand): ArticleDto {
        val article = Article(
            title = command.title,
            content = command.content,
            viewCount = 0,
            createdAt = LocalDateTime.now(),
        )
        val save = articleRepository.save(article)
        return ArticleDto(save)
    }
    fun updateArticle(articleId: Long, command: UpdateArticleCommand) {
        val article = articleRepository.findById(articleId) ?: throw NotFoundException("존재 하지 않는 게시글 입니다.")
        val update = article.update(command.title, command.content)
        articleRepository.save(update)
    }

    fun deleteArticle(articleId: Long) {
        val article = articleRepository.findById(articleId) ?: throw NotFoundException("존재 하지 않는 게시글 입니다.")
        articleRepository.delete(article)
    }

    fun increaseViewCount(articleId: Long, userId: Long) {
        val article = articleRepository.findById(articleId) ?: throw NotFoundException("존재 하지 않는 게시글 입니다.")


    }
}