package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.comment

import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.comment.CommentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class CommentQueryService(
    val commentRepository: CommentRepository,
) {
    fun fetchByArticleId(articleId: Long): List<CommentDto> {
        return commentRepository.findByArticleId(articleId).map { CommentDto(it) }
    }

}