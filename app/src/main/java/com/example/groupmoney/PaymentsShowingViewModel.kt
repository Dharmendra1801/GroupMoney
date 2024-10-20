package com.example.groupmoney

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PaymentsShowingViewModel(finalDetails: FinalDetails): ViewModel() {
    private val _result = mutableStateOf(FinalDetails(finalDetails.allEntry,finalDetails.nameList))
    val result: State<FinalDetails> = _result
}