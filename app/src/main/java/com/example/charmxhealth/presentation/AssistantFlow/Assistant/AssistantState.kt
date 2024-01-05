package com.example.charmxhealth.presentation.AssistantFlow.Assistant

import com.example.webdigitaltask.domain.model.Gemini

data class AssistantState (
    val articles : List<Gemini> = emptyList()
)