package com.example.groupmoney

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun entryPopUp() {
    AlertDialog(onDismissRequest = {}) {
        Text(text = "Enter Details")
    }
}


@Preview(showBackground = true)
@Composable
fun entryPopUpPreview() {
    entryPopUp()
}