package com.example.groupmoney
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SimpleViewModelFactory(private val finalDetails: FinalDetails) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CalculationsViewModel(finalDetails) as T
    }
}
