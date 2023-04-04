package com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.`in`.web

import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.comment.CommentCommandService
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.comment.CommentDto
import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.comment.CommentQueryService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("comments")
@RestController
class CommentController(
    val commentQueryService: CommentQueryService,
    val commentCommandService: CommentCommandService,
) {

    @GetMapping
    fun getComments(@RequestParam articleId: Long): List<CommentDto> {
        return commentQueryService.fetchByArticleId(articleId)
    }

    @PostMapping
    fun createComment() {

    }

    @PutMapping("{commentId}")
    fun updateComment(@PathVariable commentId: Long) {

    }

    @DeleteMapping("{commentId}")
    fun deleteComment(@PathVariable commentId: Long) {

    }

}