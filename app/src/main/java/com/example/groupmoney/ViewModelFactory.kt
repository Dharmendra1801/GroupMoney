package com.example.groupmoney
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class CalculationsViewModelFactory(private val finalDetails: FinalDetails) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculationsViewModel::class.java)) {
            return CalculationsViewModel(finalDetails) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

