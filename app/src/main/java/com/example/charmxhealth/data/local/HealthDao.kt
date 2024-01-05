package com.example.webdigitaltask.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.webdigitaltask.domain.model.Gemini
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthDao {

        @Query("SELECT * FROM Gemini")
        fun getAllCars(): Flow<List<Gemini>>

        @Upsert
        fun insertCar(gemini: Gemini)

        @Delete
        fun deleteCar(gemini: Gemini)

}