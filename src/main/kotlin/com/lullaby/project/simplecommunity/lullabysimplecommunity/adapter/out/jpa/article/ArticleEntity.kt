package com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.out.jpa.article

import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.article.Article
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "lullaby_community_article")
@Entity
data class ArticleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    var id: Long? = null,
    var title: String = "",
    var content: String = "",
    var viewCount: Int = 0,
    var createdAt: LocalDateTime = LocalDateTime.MIN,
) {
    fun toDomain(): Article {
        return Article(
            this.id,
            this.title,
            this.content,
            this.viewCount,
            this.createdAt,
        )
    }
}