package com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.out.jpa.comment

import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.comment.Comment
import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.comment.CommentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CommentRepositoryAdapter(
    val commentJpaRepository: CommentJpaRepository,
): CommentRepository {
    override fun save(comment: Comment): Comment {
        val commentEntity: CommentEntity =
            if (comment.id == null)
                CommentEntity()
            else
                commentJpaRepository.findByIdOrNull(comment.id)!!

        commentEntity.content = comment.content
        commentEntity.writerId = comment.writerId
        commentEntity.articleId = comment.articleId
        commentEntity.createdAt = comment.createdAt

        return commentJpaRepository.save(commentEntity).toDomain()
    }

    override fun findById(commentId: Long): Comment? {
        return commentJpaRepository.findByIdOrNull(commentId)?.toDomain()
    }

    override fun findByArticleId(articleId: Long): List<Comment> {
        return commentJpaRepository
            .findByArticleId(articleId)
            .map(CommentEntity::toDomain)
    }

    override fun delete(commentId: Long) {
        commentJpaRepository.deleteById(commentId)
    }
}