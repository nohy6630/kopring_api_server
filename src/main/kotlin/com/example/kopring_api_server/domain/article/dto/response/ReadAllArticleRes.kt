package com.example.kopring_api_server.domain.article.dto.response

import com.example.kopring_api_server.domain.article.entity.Article

data class ReadAllArticleRes(
    val size: Int,
    val articles: List<ReadArticleRes>
) {
    companion object {
        fun of(articles: List<Article>): ReadAllArticleRes {
            val items: List<ReadArticleRes> = articles.map { ReadArticleRes.of(it) }
            return ReadAllArticleRes(items.size, items)
        }
    }
}