package com.example.groupmoney

data class EntryData(val name: List<String>,
                     val amount: Int,
                     val method: String = "N/A",
                     val forWhom: List<String>)


