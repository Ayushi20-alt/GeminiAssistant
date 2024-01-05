package com.example.charmxhealth.presentation.AssistantFlow.Assistant

import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmxhealth.data.repository.HealthRepositoryImp
import com.example.webdigitaltask.domain.model.Gemini
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.google.ai.client.generativeai.type.content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

// setup of how to use generative sdk
@HiltViewModel
class assistantViewModel @Inject constructor (
    private val repo : HealthRepositoryImp
)  : ViewModel() {

    private val _uistate : MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Initial)
    val uiState = _uistate.asStateFlow()

    private val _state = mutableStateOf(AssistantState())
    val state : State<AssistantState> = _state

    private val _roomstate = repo.selectedArticle()
    val roomstate : Flow<List<Gemini>> = _roomstate
//
//    private suspend fun getArticles(){
//        repo.selectedArticle().onEach {
//            _state.value = _state.value.copy(articles = it.asReversed())
//        }.launchIn(viewModelScope)   // we are collecting this
//    }

    suspend fun upsertarticle(article: Gemini) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.upsertArticle(article)
        }
    }
    suspend fun deletearticle(article: Gemini) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteArticle(article)
        }
    }

    private var generativeModel : GenerativeModel

    init {
        val config = generationConfig{
            temperature = 0.70f //0 to 1, if it is zero then there will be more generic response and no creativity
        }
        generativeModel = GenerativeModel(
            modelName =  "gemini-pro-vision",
            apiKey = com.example.charmxhealth.BuildConfig.apiKey,
            generationConfig = config
        )
    }

    // we will accept user input string and bitmap of images
    fun questioning(userInput : String, selectedImages : List<Bitmap>){
        _uistate.value = HomeUiState.Loading
        val prompt = "Take a look at images, and then answer the following question : $userInput"

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val content = content {
                    for (bitmap in selectedImages){
                        image(bitmap)
                    }
                    text(prompt)
                }
                var output = " "
                // if we will only use generative content that will give ans after full generation but if stream it will give a stream
                generativeModel.generateContentStream(content).collect{
                    output += it.text
                    _uistate.value = HomeUiState.Success(Gemini(query = userInput, promptText = output))

                    val prompt = Gemini(query = userInput,promptText = output)
                    runBlocking {
                        upsertarticle(prompt)
                    }
                }
            }catch (e: Exception){
                _uistate.value = HomeUiState.Error(e.localizedMessage ?: "Error in Generating Content")
            }
        }
    }


}



sealed interface HomeUiState{

    object Loading: HomeUiState
    data class Success(
        val articles: Gemini
    ): HomeUiState
    data class Error(val error : String): HomeUiState
    object Initial: HomeUiState
}

