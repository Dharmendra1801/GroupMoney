package com.example.groupmoney

import java.util.jar.JarEntry

data class DataDetailClass(val paidBy: List<String>,
                           var amount: Int,
                           val forWhom: List<String>)

data class FinalDetails(var allEntry: List<DataDetailClass>)


