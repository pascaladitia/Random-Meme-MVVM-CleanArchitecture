package com.pascal.randommeme.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pascal.randommeme.domain.model.ResponseMeme
import com.pascal.randommeme.domain.usecase.GetMemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMemeUseCase: GetMemeUseCase
): ViewModel() {
    var responseMemeData = MutableLiveData<ResponseMeme>()
    var responseMeme: LiveData<ResponseMeme> = responseMemeData

    var isError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.value = false
    }

    fun loadMeme() {
        getMemeUseCase.execute(
            onSuccess = {
                isLoading.value = true
                responseMemeData.value = it
            },
            onError = {
                isError.value = it
            }
        )
    }
}