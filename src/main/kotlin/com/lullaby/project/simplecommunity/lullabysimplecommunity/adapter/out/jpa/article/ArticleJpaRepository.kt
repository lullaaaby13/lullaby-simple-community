package com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.out.jpa.article

import org.springframework.data.jpa.repository.JpaRepository

interface ArticleJpaRepository: JpaRepository<ArticleEntity, Long> {
}