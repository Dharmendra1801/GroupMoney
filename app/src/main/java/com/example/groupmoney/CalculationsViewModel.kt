package com.example.groupmoney


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class CalculationsViewModel(finalDetails: FinalDetails): ViewModel() {

    private val _result = mutableStateOf(Result(emptyList(), emptyList(), emptyList(), emptyArray()))
    val result: State<Result> = _result

    private val detailedResult: MutableList<OneOnOnePayments> = mutableListOf()
    val normalResult: MutableList<OneOnOnePayments> = mutableListOf()
    val simplifiedResult: MutableList<OneOnOnePayments> = mutableListOf()


//    val list1 = listOf<String>("Uday","Prince","Tipu","Nagu")
//    val indi1 = IndividualPart("Uday",100)
//    val indi2 = IndividualPart("Prince",200)
//    val indi3 = IndividualPart("Uday",1000)
//    val indi4 = IndividualPart("Nagu",2000)
//    val listOfIndi1 = listOf<IndividualPart>(indi1, indi2)
//    val listOfIndi2 = listOf<IndividualPart>(indi3, indi4)
//    val helper1 = DataDetailClass(listOfIndi1,0,list1)
//    val helper2 = DataDetailClass(listOfIndi2,0,list1)
//    val finale = listOf<DataDetailClass>(helper1, helper2)

    private val nameList = finalDetails.nameList
    private val allEntry = finalDetails.allEntry

    init {
        calculations()
    }

    private fun calculations() {
        var matrix = Array(nameList.size) { Array(nameList.size) { 0 } }
        var paymentInfoArray: Array<MutableList<DataDetailClass>> = Array(nameList.size) { mutableListOf() }
        var finalPayments = Array(nameList.size) { 0 }
        allEntry.forEach { payments ->
            val noOfPeople = payments.forWhom.size
            payments.listOfIndividualPart.forEach { each ->
                var amount = each.amount
                val idx = nameList.indexOf(each.name)
                finalPayments[idx] += amount
                amount /= noOfPeople
                payments.forWhom.forEach { name ->
                    val ind = nameList.indexOf(name)
                    paymentInfoArray[ind].add(payments)
                    matrix[idx][ind] += amount
                }
            }
        }
        converter(matrix,detailedResult,nameList)
        _result.value = _result.value.copy(detailed = detailedResult, paymentInfo = paymentInfoArray)

        val newMat: Array<Array<Int>> = Array(matrix.size) { i ->
            matrix[i].clone()
        }
    }

    private fun converter(m: Array<Array<Int>>, l: MutableList<OneOnOnePayments>, nl: List<String>) {
        for (i in m.indices) {
            var check = false
            var entry = OneOnOnePayments("",nl[i],0)
            for (j in m[i].indices) {
                check = true
                if (m[i][j]==0) {continue}
                if (i==j) {continue}
                entry.from = nl[j]
                entry.amount = m[i][j]
            }
            if (check) {
                l.add(entry.copy())
            }
        }
    }
}

