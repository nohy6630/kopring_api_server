package com.example.kopring_api_server.domain.article.service

import com.example.kopring_api_server.domain.article.dto.request.CreateArticleReq
import com.example.kopring_api_server.domain.article.dto.request.UpdateArticleReq
import com.example.kopring_api_server.domain.article.dto.response.ReadAllArticleRes
import com.example.kopring_api_server.domain.article.dto.response.ReadArticleRes
import com.example.kopring_api_server.domain.article.entity.Article
import com.example.kopring_api_server.domain.article.repository.ArticleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ArticleService(
    private val articleRepository: ArticleRepository
) {
    @Transactional
    fun create(createArticleReq: CreateArticleReq): Long {
        val article: Article = articleRepository.save(
            Article.of(
                createArticleReq.title,
                createArticleReq.content
            )
        )
        return article.id
    }

    fun read(articleId: Long): ReadArticleRes {
        val article: Article = articleRepository.findByIdOrNull(articleId)
            ?: throw IllegalArgumentException("article is not found")
        return ReadArticleRes.of(article)
    }

    fun readAll(): ReadAllArticleRes {
        val articles: List<Article> = articleRepository.findAll()
        return ReadAllArticleRes.of(articles)
    }

    @Transactional
    fun update(articleId: Long, updateArticleReq: UpdateArticleReq) {
        val article: Article = articleRepository.findByIdOrNull(articleId)
            ?: throw IllegalArgumentException("article is not found")
        article.title = updateArticleReq.title
        article.content = updateArticleReq.content
    }

    @Transactional
    fun delete(articleId: Long) {
        val article: Article = articleRepository.findByIdOrNull(articleId)
            ?: throw IllegalArgumentException("article is not found")
        articleRepository.delete(article)
    }
}