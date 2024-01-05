package com.example.charmxhealth.presentation.AssistantFlow.Assistant

import com.example.webdigitaltask.domain.model.Gemini

sealed interface AssistantEvent {

    data class DeleteArticle(val article : Gemini): AssistantEvent
}