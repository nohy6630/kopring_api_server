package com.example.kopring_api_server.domain.article.dto.request

import jakarta.validation.constraints.NotBlank
import lombok.Getter
import lombok.NoArgsConstructor

data class CreateArticleReq(
    @field:NotBlank(message = "title is cannot be blank")
    val title: String,
    @field:NotBlank(message = "content is cannot be blank")
    val content: String
)