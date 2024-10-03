package com.example.groupmoney

import android.content.ClipData.Item
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
fun dataEntryScreen(finalDetails: FinalDetails) {
    var name by remember{ mutableStateOf("") }
    val nameList = remember {mutableStateListOf<String>()}
    val tempFinalDetails = remember {mutableStateListOf<DataDetailClass>()}
    var namePopup by remember { mutableStateOf(false) }
    var detailPopup by remember { mutableStateOf(false) }
    val listOfPayers = remember { mutableStateListOf<String>() }
    var amount by remember { mutableStateOf("") }
    val listOfBeingPaidFor = remember { mutableStateListOf<String>() }
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "Enter all the Payments Details ",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            if (nameList.isEmpty()) {
                Button(onClick = {namePopup=true}, modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)) {
                    Text(text = "Add Names")
                }
            }
            else {
                Button(
                    onClick = {detailPopup=true}, modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Add Transactions")
                }
            }
            if (namePopup) {
                AlertDialog(onDismissRequest = {namePopup=false},
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.White)
                ) {
                    Column {
                        Text(text = "Enter the Names",
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black)

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
//                                Toast.makeText(context, "${name} Added", Toast.LENGTH_SHORT).show()
                                name = ""
                            }) {
                                Text(text = "Next")
                            }
                            Button(onClick = {
                                nameList.add(name)
                                namePopup = false
                            }) {
                                Text(text = "Done")
                            }
                        }
                    }
                }
            }
            if (detailPopup) {
                AlertDialog(onDismissRequest = {detailPopup=false},
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.White)
                ) {
                    Column {
                        Text(text = "Enter Transaction Details",
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black)

                        Column(modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Select people who made the transactions",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                fontSize = 15.sp)
                            Box {
                                var dropdown by remember{ mutableStateOf(false) }
                                Button(onClick = { dropdown = !dropdown }, modifier = Modifier.padding(8.dp)) {
                                    Text(text = "Drop Down")
                                    Icon(Icons.Default.ArrowDropDown,contentDescription = null)
                                }
                                DropdownMenu(expanded = dropdown, onDismissRequest = { dropdown = false }) {
                                    nameList.forEach {item ->
                                        DropdownMenuItem(
                                            text = { Text(item) },
                                            onClick = {
                                                if (item in listOfPayers) {}
                                                else {
                                                    listOfPayers.add(item)
                                                }
                                            })
                                    }
                                }
                            }
                            Text(text = "Select people for whom the transactions were done for",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                fontSize = 15.sp)
                            Box {
                                var dropdown by remember{ mutableStateOf(false) }
                                Button(onClick = { dropdown = !dropdown }, modifier = Modifier.padding(8.dp)) {
                                    Text(text = "Drop Down")
                                    Icon(Icons.Default.ArrowDropDown,contentDescription = null)
                                }
                                DropdownMenu(expanded = dropdown, onDismissRequest = { dropdown = false }) {
                                    nameList.forEach {item ->
                                        DropdownMenuItem(
                                            text = { Text(item) },
                                            onClick = {
                                                if (item in listOfBeingPaidFor) {}
                                                else {
                                                    listOfBeingPaidFor.add(item)
                                                }
                                            })
                                    }
                                }
                            }
                        }

                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Enter Amount",
                                modifier = Modifier
                                    .padding(24.dp)
                                    .padding(top = 17.dp),
                                fontSize = 15.sp)
                            OutlinedTextField(value = amount,
                                label = {Text("")},
                                onValueChange = {
                                    amount = it},
                                modifier = Modifier.padding(16.dp)
                            )
                        }

                        Row(horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)) {
                            Button(onClick = {
                                val entry = DataDetailClass(listOfPayers.toList(),amount.toInt(),listOfBeingPaidFor.toList())
                                tempFinalDetails.add(entry)
                                listOfPayers.clear()
                                amount = ""
                                listOfBeingPaidFor.clear()
                            }) {
                                Text(text = "Next")
                            }
                            Button(onClick = {
                                val entry = DataDetailClass(listOfPayers.toList(),amount.toInt(),listOfBeingPaidFor.toList())
                                listOfBeingPaidFor.clear()
                                listOfPayers.clear()
                                amount=""
                                tempFinalDetails.add(entry.copy())
                                finalDetails.allEntry = tempFinalDetails
                                detailPopup = false
                            }) {
                                Text(text = "Done")
                            }
                        }
                    }
                }
            }
            else {
                showTransactions(tempFinalDetails)
            }
        }
    }
}


@Composable
fun showTransactions(temp: List<DataDetailClass>) {
    LazyColumn {
        items(temp) {
            entries->
            entryDesign(entries)
        }
    }
}

@Composable
fun entryDesign(entry: DataDetailClass) {
    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(), horizontalArrangement = Arrangement.Absolute.SpaceBetween) {
        Row {
            Icon(Icons.Default.Face, contentDescription = null, modifier = Modifier.padding(8.dp))
            Column(modifier = Modifier.padding(8.dp)) {
                Text("Paid By:")
                Row {
                    entry.paidBy.forEach { name ->
                        Text(text = "$name  ", textAlign = TextAlign.Left)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text("Paid For:")
                Row {
                    entry.forWhom.forEach { name ->
                        Text(text = "$name  ", textAlign = TextAlign.Left)
                    }
                }
            }
        }
        Text ("Amount Paid: ${entry.amount}" , textAlign = TextAlign.Right , modifier = Modifier.padding(8.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun dataEntryScreenPreview(){
    dataEntryScreen(finalDetails = FinalDetails(emptyList()))
}