package com.example.charmxhealth.data.repository

import com.example.charmxhealth.domain.repository.HealthRepository
import com.example.webdigitaltask.data.local.HealthDao
import com.example.webdigitaltask.domain.model.Gemini
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HealthRepositoryImp @Inject constructor(
    private val healthDao: HealthDao
) : HealthRepository {

    override suspend fun upsertArticle(article: Gemini) {
        healthDao.insertCar(article)
    }

    override suspend fun deleteArticle(article: Gemini) {
        healthDao.deleteCar(article)
    }

    override fun selectedArticle(): Flow<List<Gemini>> {
       return healthDao.getAllCars()
    }

}