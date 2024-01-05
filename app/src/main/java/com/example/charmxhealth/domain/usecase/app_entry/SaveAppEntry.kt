package com.example.charmxhealth.domain.usecase.app_entry

import com.example.charmxhealth.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
       localUserManager.saveAppEntry()
    }
}