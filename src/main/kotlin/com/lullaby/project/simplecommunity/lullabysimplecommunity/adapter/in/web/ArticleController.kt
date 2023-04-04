package com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.`in`.web

import com.lullaby.project.simplecommunity.lullabysimplecommunity.application.article.*
import org.springframework.web.bind.annotation.*
import java.nio.file.attribute.UserPrincipal
import java.security.Principal
import java.time.LocalDateTime

@RequestMapping("articles")
@RestController
class ArticleController(
    val articleQueryService: ArticleQueryService,
    val articleCommandService: ArticleCommandService,
) {

    @GetMapping
    fun getArticles(): List<ArticleDto> {
        return articleQueryService.getArticles()
    }

    @GetMapping("{articleId}")
    fun getArticle(
        principal: Principal,
        @PathVariable articleId: Long
    ): ArticleDto {
        return articleQueryService.getArticle(articleId = articleId, userId = principal.name.toLong())
    }

    @PostMapping
    fun creatArticle(@RequestBody request: CreateArticleRequest) {
        val command = CreateArticleCommand(
            request.title,
            request.content
        )
        articleCommandService.createArticle(command)
    }

    @PutMapping("{articleId}")
    fun updateArticle(
        @PathVariable articleId: Long,
        @RequestBody request: UpdateArticleRequest
    ) {
        val command = UpdateArticleCommand(
            request.title,
            request.content
        )
        articleCommandService.updateArticle(articleId, command)
    }

    @DeleteMapping("{articleId}")
    fun deleteArticle(@PathVariable articleId: Long) {
        articleCommandService.deleteArticle(articleId)
    }

}

data class CreateArticleRequest(val title: String, val content: String)
data class UpdateArticleRequest(val title: String, val content: String)

