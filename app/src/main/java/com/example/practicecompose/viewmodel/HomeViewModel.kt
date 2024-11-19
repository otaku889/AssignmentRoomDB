package com.example.practicecompose.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicecompose.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject
constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    val Emails = MutableLiveData<String>()

     fun fetchData() {
         viewModelScope.launch {

             try {
             val result = repository.getSupabaseAccount()
            result.forEach{
                Emails.postValue(it.emails)
                Log.i("YUKI","Fetch Data worked ${it.emails}")
            }
         }
             catch (e: Exception){
             Log.i("YUKI","Error: $e")
         }

         }

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("YUKI","ViewModelCleared")
    }
}