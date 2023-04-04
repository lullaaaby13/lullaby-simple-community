package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.article

data class CreateArticleCommand(val title: String, val content: String)
data class UpdateArticleCommand(val title: String, val content: String)
