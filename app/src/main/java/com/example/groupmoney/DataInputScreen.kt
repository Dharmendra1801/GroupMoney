package com.example.groupmoney

import android.content.ClipData.Item
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun dataEntryScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "Enter all the Payments Done Below",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = {}, modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)) {
                Text(text = "Add Transaction")
            }

//            LazyColumn(content = ) {
//            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun dataEntryScreenPreview(){
    dataEntryScreen()
}