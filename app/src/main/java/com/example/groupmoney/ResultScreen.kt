package com.example.groupmoney

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun ResultScreen(finalDetails: FinalDetails) {

    /////
//    val n1 = listOf<String>("Me","Ani","Dev","Aa","Ch","Am","Na")
//    val n2 = listOf<String>("Me","Ani","Dev","Aa")
//    val n3 = listOf<String>("Me","Dev","Aa")
//    val indi1 = IndividualPart("Dev",80)
//    val l1 = listOf<IndividualPart>(indi1)
//    val t1 = DataDetailClass(l1,80,n2)
//    val finalewaalilist = FinalDetails(finale,list1)
    //////

    val viewModelFactory = CalculationsViewModelFactory(finalDetails)
    val viewModel: CalculationsViewModel = viewModel(factory = viewModelFactory)
    val result = viewModel.result
    val normalList = result.value.normal
    val detailedList = result.value.detailed
    val simplifiedList = result.value.simplified
    var no by remember { mutableStateOf("1") }
    

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Text(text = "Result",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue)

        Row(verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            if (no!="1") {
                Button(onClick = { no = "1" }) {
                    Text(text = "Normal")
                }
            }
            if (no!="2") {
                Button(onClick = { no = "2" }) {
                    Text(text = "Detailed")
                }
            }
            if (no!="3") {
                Button(onClick = { no = "3" }) {
                    Text(text = "Simplified")
                }
            }
        }

        if (no=="1") {
            displayForList(list = normalList)
        } else if (no=="2") {
            displayForList(list = detailedList)
        } else {
            displayForList(list = simplifiedList)
        }
    }
}


@Composable
fun displayForList(list: List<OneOnOnePayments>) {
    LazyColumn {
        items(list) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Row(modifier = Modifier.padding(top = 7.dp)) {
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "${it.from} pays ${it.to}: ${it.amount}",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                }
                IconButton (onClick = {}) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = null)
                }
            }
        }
    }
    
}


@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    ResultScreen(finalDetails = FinalDetails(emptyList(), emptyList()))
}