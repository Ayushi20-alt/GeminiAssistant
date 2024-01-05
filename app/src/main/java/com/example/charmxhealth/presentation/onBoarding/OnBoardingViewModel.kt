package com.example.charmxhealth.presentation.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.charmxhealth.domain.usecase.app_entry.AppEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCase: AppEntryUseCase
):ViewModel() {

    fun OnEvent(event : OnBoardingEvent){
       when(event){
          is OnBoardingEvent.saveAppEntry->{
              saveAppEntry()
          }
       }
    }

    private fun saveAppEntry(){
       viewModelScope.launch {
          appEntryUseCase.saveAppEntry()
       }
    }
}