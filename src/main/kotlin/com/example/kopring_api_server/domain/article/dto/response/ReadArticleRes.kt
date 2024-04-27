package com.example.kopring_api_server.domain.article.dto.response

import com.example.kopring_api_server.domain.article.entity.Article

data class ReadArticleRes(
    val id: Long,
    val title: String,
    val content: String
) {
    companion object {
        fun of(article: Article): ReadArticleRes {
            return ReadArticleRes(article.id, article.title, article.content)
        }
    }
}