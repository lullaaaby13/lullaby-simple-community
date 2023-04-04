package com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.article

import java.util.*

interface ArticleRepository {
    fun findById(articleId: Long): Article?
    fun findAll(): List<Article>
    fun save(article: Article): Article
    fun delete(article: Article)
}