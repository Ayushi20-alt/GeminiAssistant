package com.example.charmxhealth.domain.usecase.app_entry

import com.example.charmxhealth.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}