package com.example.groupmoney
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class PaymentsShowingViewModelFactory(private val finalDetails: FinalDetails) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PaymentsShowingViewModel::class.java)) {
            return PaymentsShowingViewModel(finalDetails) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

