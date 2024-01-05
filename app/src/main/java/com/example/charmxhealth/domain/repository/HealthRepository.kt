package com.example.charmxhealth.domain.repository

import com.example.webdigitaltask.domain.model.Gemini
import kotlinx.coroutines.flow.Flow

interface HealthRepository {

    suspend fun upsertArticle(article: Gemini)

    suspend fun deleteArticle(article: Gemini)

    fun selectedArticle(): Flow<List<Gemini>>
}