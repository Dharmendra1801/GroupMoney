package com.example.groupmoney


import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dataEntryScreen(goToCalculationPage: (FinalDetails) -> Unit) {
    var name by remember{ mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val nameList = remember {mutableStateListOf<String>()}
    val tempFinalDetails = remember {mutableStateListOf<DataDetailClass>()}
    var namePopup by remember { mutableStateOf(false) }
    var detailPopup by remember { mutableStateOf(false) }
    val listOfPayersWithAmount = remember { mutableStateListOf<IndividualPart>() }
    val listOfPayers = remember { mutableStateListOf<String>() }
    val listOfBeingPaidFor = remember { mutableStateListOf<String>() }
    var totalAmount by remember { mutableStateOf(0) }
    val context = LocalContext.current

    /// Sample Data
//    val list1 = listOf<String>("Uday","Nagu","Prince","Tipu","Tipu","Tipu","Tipu","Tipu")
//    val indi1 = IndividualPart("Uday",100)
//    val indi2 = IndividualPart("Prince",200)
//    val indi3 = IndividualPart("Tipu",1000)
//    val indi4 = IndividualPart("Nagu",2000)
//    val listOfIndi1 = listOf<IndividualPart>(indi1, indi2)
//    val listOfIndi2 = listOf<IndividualPart>(indi3, indi4)
//    val helper1 = DataDetailClass(listOfIndi1,300,list1)
//    val helper2 = DataDetailClass(listOfIndi2,3000,list1)
//    val finale = listOf<DataDetailClass>(helper1, helper2, helper2, helper2, helper2, helper2)
    ////

    Box(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
            else if (tempFinalDetails.isEmpty()) {
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
                                Toast.makeText(context, "$name Added", Toast.LENGTH_SHORT).show()
                                name = ""
                            }) {
                                Text(text = "Next")
                            }
                            Button(onClick = {
                                nameList.add(name)
                                Toast.makeText(context, "$name Added", Toast.LENGTH_SHORT).show()
                                namePopup = false
                            }) {
                                Text(text = "Done")
                            }
                        }
                    }
                }
            }
            if (detailPopup) {
                var dropdown by remember{ mutableStateOf(false) }
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
                                Button(onClick = { dropdown = !dropdown }, modifier = Modifier.padding(top = 8.dp)) {
                                    Text(text = "Drop Down")
                                    Icon(Icons.Default.ArrowDropDown,contentDescription = null)
                                }
                                DropdownMenu(expanded = dropdown, onDismissRequest = { dropdown = false }) {
                                    nameList.forEach {item ->
                                        DropdownMenuItem(
                                            text = { Text(item) },
                                            onClick = {
                                                Toast.makeText(context, "$item Selected", Toast.LENGTH_SHORT).show()
                                                name = item
                                                if (item in listOfPayers) {}
                                                else {
                                                    listOfPayers.add(item)
                                                }
                                                dropdown = false
                                            })
                                    }
                                }
                            }


                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
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
                            Button(onClick = {
                                val entry = IndividualPart(name,amount.toInt())
                                totalAmount+=amount.toInt()
                                name = ""
                                amount = ""
                                listOfPayersWithAmount.add(entry.copy())
                                dropdown = true
                            }) {
                                Text(text = "Add more people")
                            }


                            Text(text = "Select people for whom the transactions were done for",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)
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

                        Row(horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)) {
                            Button(onClick = {
                                val indientry = IndividualPart(name,amount.toInt())
                                totalAmount+=amount.toInt()
                                listOfPayersWithAmount.add(indientry.copy())
                                val entry = DataDetailClass(
                                    listOfPayersWithAmount.toList(),
                                    totalAmount,
                                    listOfBeingPaidFor.toList()
                                )
                                amount = ""
                                totalAmount = 0
                                tempFinalDetails.add(entry)
                                listOfPayers.clear()
                                listOfPayersWithAmount.clear()
                                listOfBeingPaidFor.clear()
                                Toast.makeText(context, "Transaction Added", Toast.LENGTH_SHORT)
                                    .show()
                            }) {
                                Text(text = "Next")
                            }
                            Button(onClick = {
                                val indientry = IndividualPart(name,amount.toInt())
                                totalAmount+=amount.toInt()
                                listOfPayersWithAmount.add(indientry.copy())
                                val entry = DataDetailClass(
                                    listOfPayersWithAmount.toList(),
                                    totalAmount,
                                    listOfBeingPaidFor.toList()
                                )
                                tempFinalDetails.add(entry)
                                listOfPayers.clear()
                                listOfPayersWithAmount.clear()
                                listOfBeingPaidFor.clear()
                                Toast.makeText(context, "Transaction Added", Toast.LENGTH_SHORT)
                                    .show()
                                detailPopup = false
                                amount = ""
                            }) {
                                Text(text = "Done")
                            }
                        }
                    }
                }
            }
            else {
                Column(verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    if (!tempFinalDetails.isEmpty()) {
                        val finalDetails = FinalDetails(tempFinalDetails,nameList)
                        Button(onClick = {
                            goToCalculationPage(finalDetails)
                        }, modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)) {
                            Text("Calculate")
                        }
                    }
                    showTransactions(tempFinalDetails)
                }
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
        .fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.Start) {
            Icon(Icons.Default.Face, contentDescription = null, modifier = Modifier.padding(8.dp))
            Column {
                Text("Paid By and Amount:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp)
                entry.listOfIndividualPart.forEach { item ->
                    Text(text = "${item.name}: ${item.amount}  ", textAlign = TextAlign.Left)
                }
                Column {
                    Text(
                        "Amount Paid: ",
                        textAlign = TextAlign.Right,
                        modifier = Modifier.padding(top = 16.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text("${entry.totalAmount}")
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround) {
            Column {
                Text("Paid For:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp)
                entry.forWhom.forEach { name ->
                    Text(text = "$name  ", textAlign = TextAlign.Left)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun dataEntryScreenPreview(){
    dataEntryScreen({})
}