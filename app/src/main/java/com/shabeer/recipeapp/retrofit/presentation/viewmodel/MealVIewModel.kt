package com.shabeer.recipeapp.retrofit.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shabeer.recipeapp.retrofit.data.models.MealDB
import com.shabeer.recipeapp.retrofit.data.repositories.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(private val repository: MealRepository) : ViewModel() {


    private val _storeMealData = MutableLiveData<MealDB>()
    val storeMealData: LiveData<MealDB> = _storeMealData


    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading


    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        getMealDataFromApi()
    }

    fun getMealDataFromApi() {

        _isLoading.value = true


        viewModelScope.launch {
            try {
                val response = repository.getAllData()
                if (response.isSuccessful) {
                    _storeMealData.value = response.body()
                } else {
                    _errorMessage.value = response.message()
                    Log.d("MealViewModel", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
                Log.d("MealViewModel", "Exception: ${e.localizedMessage}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
