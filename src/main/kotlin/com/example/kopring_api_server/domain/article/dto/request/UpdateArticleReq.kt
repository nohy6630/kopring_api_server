package com.example.kopring_api_server.domain.article.dto.request

import jakarta.validation.constraints.NotBlank

data class UpdateArticleReq(
    @field:NotBlank(message = "title is cannot be blank")
    val title: String,
    @field:NotBlank(message = "content is cannot be blank")
    val content: String
)