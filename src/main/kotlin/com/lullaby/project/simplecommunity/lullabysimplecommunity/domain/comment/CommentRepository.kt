package com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.comment

interface CommentRepository {
    fun save(comment: Comment): Comment
    fun findById(commentId: Long): Comment?
    fun findByArticleId(articleId: Long): List<Comment>
    fun delete(commentId: Long)
}