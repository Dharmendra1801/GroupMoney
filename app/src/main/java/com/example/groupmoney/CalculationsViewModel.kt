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
    private val normalResult: MutableList<OneOnOnePayments> = mutableListOf()
    private val simplifiedResult: MutableList<OneOnOnePayments> = mutableListOf()


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
                if (amount%noOfPeople!=0) {
                    amount = amount/noOfPeople + 1
                } else {
                    amount /= noOfPeople
                }
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

        for (i in newMat.indices) {
            for (j in newMat[i].indices) {
                if (newMat[i][j]==0) {continue}
                if (i==j) {continue}
                if (newMat[i][j]>=newMat[j][i]) {
                    newMat[i][j]-=newMat[j][i]
                    newMat[j][i]=0
                }
                else {
                    newMat[j][i]-=newMat[i][j]
                    newMat[i][j]=0
                }
            }
        }

        converter(newMat,normalResult,nameList)
        _result.value = _result.value.copy(normal = normalResult)
    }

    private fun converter(m: Array<Array<Int>>, l: MutableList<OneOnOnePayments>, nl: List<String>) {
        for (i in m.indices) {
            for (j in m[i].indices) {
                if (m[i][j]==0) {continue}
                if (i==j) {continue}
                var entry = OneOnOnePayments(nl[j],nl[i],m[i][j])
                l.add(entry.copy())
            }
        }
    }
}

