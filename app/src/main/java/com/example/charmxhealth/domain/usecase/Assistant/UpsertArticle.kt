package com.example.charmxhealth.domain.usecase.Assistant

import com.example.charmxhealth.domain.repository.HealthRepository
import com.example.webdigitaltask.domain.model.Gemini

class UpsertArticle(
    private val healthRepository: HealthRepository
) {

    suspend operator fun invoke(article: Gemini){
        healthRepository.upsertArticle(article)
    }
}