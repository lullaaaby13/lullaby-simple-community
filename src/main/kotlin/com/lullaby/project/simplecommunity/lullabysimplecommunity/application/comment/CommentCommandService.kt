package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.comment

import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.NotFoundException
import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.comment.Comment
import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.comment.CommentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@Service
class CommentCommandService(
    val commentRepository: CommentRepository,
) {

    fun createComment(command: CreateCommentCommand) {
        val comment = Comment(
            content = command.content,
            writerId = command.writerId,
            articleId = command.articleId,
            createdAt = LocalDateTime.now(),
        )
        commentRepository.save(comment)
    }

    fun updateComment(commentId: Long, command: UpdateCommentCommand) {
        val comment: Comment = commentRepository.findById(commentId) ?: throw NotFoundException("존재 하지 않는 댓글 입니다.")
        val updated = comment.update(command.content)
        commentRepository.save(updated)
    }
    fun deleteComment(commentId: Long) {
        commentRepository.delete(commentId)
    }

}

data class CreateCommentCommand(
    val content: String,
    val writerId: Long,
    val articleId: Long,
)
data class UpdateCommentCommand(val content: String)