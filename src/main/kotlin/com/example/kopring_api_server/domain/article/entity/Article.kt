package com.example.kopring_api_server.domain.article.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.Builder
import lombok.NoArgsConstructor

@Entity
@NoArgsConstructor
class Article(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    var title: String,

    var content: String
) {
    companion object {
        fun of(title: String, content: String): Article {
            return Article(title = title, content = content)
        }
    }
}