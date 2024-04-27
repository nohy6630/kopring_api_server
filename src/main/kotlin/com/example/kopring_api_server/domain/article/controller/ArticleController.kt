package com.example.kopring_api_server.domain.article.controller

import com.example.kopring_api_server.domain.article.dto.request.CreateArticleReq
import com.example.kopring_api_server.domain.article.dto.request.UpdateArticleReq
import com.example.kopring_api_server.domain.article.dto.response.ReadAllArticleRes
import com.example.kopring_api_server.domain.article.dto.response.ReadArticleRes
import com.example.kopring_api_server.domain.article.service.ArticleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/articles")
class ArticleController(
    private val articleService: ArticleService
) {
    @PostMapping
    fun create(@RequestBody createArticleReq: CreateArticleReq): ResponseEntity<Unit> {
        val articleId: Long = articleService.create(createArticleReq)
        return ResponseEntity
            .created(URI.create("/articles/$articleId"))
            .build()
    }

    @GetMapping("/{articleId}")
    fun read(@PathVariable articleId: Long): ResponseEntity<ReadArticleRes> {
        return ResponseEntity
            .ok(articleService.read(articleId))
    }

    @GetMapping
    fun readAll(): ResponseEntity<ReadAllArticleRes> {
        return ResponseEntity
            .ok(articleService.readAll())
    }

    @PutMapping("/{articleId}")
    fun update(@PathVariable articleId: Long, @RequestBody updateArticleReq: UpdateArticleReq): ResponseEntity<Unit> {
        articleService.update(articleId, updateArticleReq)
        return ResponseEntity
            .ok().build()
    }

    @DeleteMapping("/{articleId}")
    fun delete(@PathVariable articleId: Long): ResponseEntity<Unit> {
        articleService.delete(articleId)
        return ResponseEntity
            .ok().build()
    }
}