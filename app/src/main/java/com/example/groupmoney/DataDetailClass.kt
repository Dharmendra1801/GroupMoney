package com.example.groupmoney

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataDetailClass(val listOfIndividualPart: List<IndividualPart>,
                           val totalAmount: Int,
                           val forWhom: List<String>) : Parcelable
@Parcelize
data class IndividualPart(val name: String,
                          val amount: Int) : Parcelable

@Parcelize
data class FinalDetails(var allEntry: List<DataDetailClass>,
                        var nameList: List<String>) : Parcelable


data class OneOnOnePayments(
    var from: String,
    var to: String,
    var amount: Int)

data class Result(val normal: List<OneOnOnePayments>,
                  var detailed: List<OneOnOnePayments>,
                  val simplified: List<OneOnOnePayments>,
                  val paymentInfo: Array<MutableList<DataDetailClass>>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Result

        if (normal != other.normal) return false
        if (detailed != other.detailed) return false
        if (simplified != other.simplified) return false
        return paymentInfo.contentEquals(other.paymentInfo)
    }

    override fun hashCode(): Int {
        var result = normal.hashCode()
        result = 31 * result + detailed.hashCode()
        result = 31 * result + simplified.hashCode()
        result = 31 * result + paymentInfo.contentHashCode()
        return result
    }
}



