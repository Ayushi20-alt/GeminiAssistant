package com.example.webdigitaltask.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Gemini(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val query : String,
    val promptText: String
)