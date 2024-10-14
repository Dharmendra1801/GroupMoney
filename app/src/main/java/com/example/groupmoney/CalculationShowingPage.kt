package com.example.groupmoney

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory

@Composable
fun CalculationsScreen(finalDetails: FinalDetails) {

    val viewModelObject: CalculationsViewModel = viewModel(factory = viewModelFactory { finalDetails })

}