package com.example.kopring_api_server.domain.article.controller

import com.example.kopring_api_server.domain.article.dto.request.CreateArticleReq
import com.example.kopring_api_server.domain.article.dto.request.UpdateArticleReq
import com.example.kopring_api_server.domain.article.dto.response.ReadAllArticleRes
import com.example.kopring_api_server.domain.article.dto.response.ReadArticleRes
import com.example.kopring_api_server.domain.article.entity.Article
import com.example.kopring_api_server.domain.article.service.ArticleService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class ArticleControllerTest {
    @Mock
    private lateinit var articleService: ArticleService

    @InjectMocks
    private lateinit var articleController: ArticleController

    private lateinit var mockMvc: MockMvc

    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build()
        objectMapper = ObjectMapper()
    }

    @Test
    fun create() {
        //given
        val createArticleReq = CreateArticleReq(
            title = "title",
            content = "content"
        )
        given(articleService.create(createArticleReq)).willReturn(1L)

        //when
        val result = mockMvc.perform(
            post("/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createArticleReq))
        )

        //then
        result
            .andExpect(status().isCreated)
            .andExpect(header().string("Location", "/articles/1"))
    }

    @Test
    fun read() {
        //given
        given(articleService.read(1L)).willReturn(
            ReadArticleRes.of(Article.of("title", "content"))
        )

        //when
        val result = mockMvc.perform(get("/articles/1"))

        //then
        result
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title").value("title"))
            .andExpect(jsonPath("$.content").value("content"))
    }

    @Test
    fun readAll() {
        //given
        given(articleService.readAll()).willReturn(
            ReadAllArticleRes.of(listOf(Article.of("title1", "content"), Article.of("title2", "content")))
        )

        //when
        val result = mockMvc.perform(get("/articles"))

        //then
        result
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.articles[0].title").value("title1"))
            .andExpect(jsonPath("$.articles[0].content").value("content"))
            .andExpect(jsonPath("$.articles[1].title").value("title2"))
            .andExpect(jsonPath("$.articles[1].content").value("content"))
    }

    @Test
    fun update() {
        //given
        val updateArticleReq = UpdateArticleReq(
            title = "title",
            content = "content"
        )

        //when
        val result = mockMvc.perform(
            put("/articles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateArticleReq))
        )

        //then
        result.andExpect(status().isOk)
        verify(articleService).update(1L, updateArticleReq)
    }

    @Test
    fun delete() {
        //given

        //when
        val result = mockMvc.perform(delete("/articles/1"))

        //then
        result.andExpect(status().isOk)
        verify(articleService).delete(1L)
    }
}