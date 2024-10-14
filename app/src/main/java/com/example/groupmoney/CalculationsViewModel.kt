package com.example.groupmoney

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CalculationsViewModel(finalDetails: FinalDetails): ViewModel() {
    init {
        calculations()
    }
    private fun calculations() {

    }
}









//class RVM: ViewModel() {
//
//    private val _categoriesState = mutableStateOf(RState())
//    val categoriesState: State<RState> = _categoriesState
//
