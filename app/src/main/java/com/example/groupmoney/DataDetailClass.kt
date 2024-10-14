package com.example.groupmoney

import java.util.jar.JarEntry

data class DataDetailClass(val listOfIndividualPart: List<IndividualPart>,
                           val forWhom: List<String>)

data class IndividualPart(val name: String,
                          val amount: Int)

data class FinalDetails(var allEntry: List<DataDetailClass>)


