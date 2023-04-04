package com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.out.jpa.article

import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.article.Article
import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.article.ArticleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ArticleRepositoryAdapter(
    val articleJpaRepository: ArticleJpaRepository
): ArticleRepository{
    override fun findById(articleId: Long): Article? {
        val articleEntity: ArticleEntity? = articleJpaRepository.findByIdOrNull(articleId)
        return articleEntity?.toDomain()
    }

    override fun findAll(): List<Article> {
        return articleJpaRepository.findAll().map(ArticleEntity::toDomain)
    }

    override fun save(article: Article): Article {
        val articleEntity: ArticleEntity =
            if (article.id == null) ArticleEntity()
            else articleJpaRepository.findByIdOrNull(article.id) ?: throw Error()

        articleEntity.title = article.title
        articleEntity.content = article.content
        articleEntity.viewCount = article.viewCount
        articleEntity.createdAt = article.createdAt

        return articleJpaRepository.save(articleEntity)
            .toDomain()
    }

    override fun delete(article: Article) {
        articleJpaRepository.deleteById(article.id!!)
    }
}