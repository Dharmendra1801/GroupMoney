package com.example.groupmoney

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory

@Composable
fun ResultScreen() {

    /////
    val list1 = listOf<String>("Uday","Prince","Tipu","Nagu")
    val indi1 = IndividualPart("Uday",100)
    val indi2 = IndividualPart("Prince",200)
    val indi3 = IndividualPart("Uday",1000)
    val indi4 = IndividualPart("Nagu",2000)
    val listOfIndi1 = listOf<IndividualPart>(indi1, indi2)
    val listOfIndi2 = listOf<IndividualPart>(indi3, indi4)
    val helper1 = DataDetailClass(listOfIndi1,0,list1)
    val helper2 = DataDetailClass(listOfIndi2,0,list1)
    val finale = listOf<DataDetailClass>(helper1, helper2)
    val finalewaalilist = FinalDetails(finale,list1)
    //////

    val viewModelFactory = CalculationsViewModelFactory(finalewaalilist)
    val viewModel: CalculationsViewModel = viewModel(factory = viewModelFactory)
    val result = viewModel.result
    val normalList = result.value.normal
    val detailedList = result.value.detailed
    val simplifiedList = result.value.simplified
    var no by remember { mutableStateOf("1") }
    

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Result",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue)
        
        if (no=="1") {
            displayForList(list = normalList)
        } else if (no=="2") {
            displayForList(list = detailedList)
        } else {
            displayForList(list = simplifiedList)
        }

        Row(verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { no="1" }) {
                Text(text = "Normal")
            }
            Button(onClick = { no="2" }) {
                Text(text = "Detailed")
            }
            Button(onClick = { no="3" }) {
                Text(text = "Simplified")
            }
        }
    }
}


@Composable
fun displayForList(list: List<OneOnOnePayments>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(list) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "${it.from} pays ${it.to}: ${it.amount}")
            }
        }
    }
    
}


@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    ResultScreen()
}