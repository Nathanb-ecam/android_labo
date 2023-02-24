package com.example.labo1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class UiState(
    val quotes: List<Quote>
)

class QuotesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState(listOf<Quote>(Quote("Test","noting to say"))));
    val uiState : StateFlow<UiState> = _uiState.asStateFlow();

    fun update(){
        val latestQuotes = Datasource().fetchQuotes();
        val currentState = _uiState.value;
        _uiState.value = currentState.copy(quotes = latestQuotes);

    }

    fun getApiQuotes(){
        viewModelScope.launch {
            val latestQuotes = QuotesApi.retrofitService.getQuotes()
            val currentState = _uiState.value;
            try{
                _uiState.value = currentState.copy(quotes = latestQuotes["quotes"]!!);
            }
            catch (e:java.lang.Exception){

            }

            //Log.d("Result",stringResult)
        }
    }
}

