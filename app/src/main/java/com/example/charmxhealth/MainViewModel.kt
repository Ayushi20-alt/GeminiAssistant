package com.example.charmxhealth

import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmxhealth.domain.usecase.app_entry.AppEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.charmxhealth.presentation.navigation.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCase: AppEntryUseCase
): ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    private var _selectedBitmap : Bitmap? = null

    fun setSelectedBitmap(bitmap: Bitmap) {
        if (bitmap != null) {
            _selectedBitmap = bitmap
        }
    }
    fun getSelectedBitmap() : Bitmap? {
        return _selectedBitmap
    }

    private fun createDefaultBitmap(): Bitmap {
        return Bitmap.createBitmap(3, 3, Bitmap.Config.ARGB_8888)
    }

    fun onTakePhoto(bitmap: Bitmap) {
        _bitmaps.value += bitmap
    }

    init {
        appEntryUseCase.readAppEntry().onEach {shouldStartFromHomeScreen->
            if(shouldStartFromHomeScreen){
               startDestination = Route.HomeNavigation.route
            }else{
                startDestination = Route.AppStartNavigation.route
            }
            delay(3000)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}