package com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.out.jpa.comment

import org.springframework.data.jpa.repository.JpaRepository

interface CommentJpaRepository: JpaRepository<CommentEntity, Long> {
    fun findByArticleId(articleId: Long): List<CommentEntity>

}