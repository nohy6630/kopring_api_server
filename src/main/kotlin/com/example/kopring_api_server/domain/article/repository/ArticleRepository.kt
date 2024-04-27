package com.example.kopring_api_server.domain.article.repository

import com.example.kopring_api_server.domain.article.entity.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article, Long>