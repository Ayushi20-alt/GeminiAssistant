package com.example.webdigitaltask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.webdigitaltask.domain.model.Gemini

@Database(entities = [Gemini::class], version = 3)
abstract class HealthDatabase : RoomDatabase() {

    abstract fun carDao() : HealthDao
}