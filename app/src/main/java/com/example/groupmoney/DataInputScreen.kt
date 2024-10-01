package com.example.groupmoney

import android.content.ClipData.Item
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dataEntryScreen() {
    var noOfPeople by remember{ mutableStateOf("") }
    var name by remember{ mutableStateOf("") }
    val nameList = remember {mutableStateListOf<String>()}
    var popup by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "Enter all the Payments Details",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            if (nameList.isEmpty()) {
                Button(onClick = {popup=true}, modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)) {
                    Text(text = "Add Names")
                }
            } else {
                Button(
                    onClick = {}, modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Add Transactions")
                }
            }
            if (popup) {
                AlertDialog(onDismissRequest = {popup=false}) {
                    Column {
                        Text(text = "Enter the Names",
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White)

                        OutlinedTextField(value = name,
                            label = {Text("")},
                            onValueChange = {
                                name = it},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                        Row(horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)) {
                            Button(onClick = {
                                nameList.add(name)
                                name = ""
                            }) {
                                Text(text = "Next Name")
                            }
                            Button(onClick = { popup = false }) {
                                Text(text = "Done")
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun dataEntryScreenPreview(){
    dataEntryScreen()
}