package com.example.plantapp.model

import android.os.Parcelable

data class Plant(
    var plantName: String = "",
    var imageUrl: String = "",
    var otherFields: String = "",
    var documentId: String = "",

    val name: String = "",  // Başlangıç değeri ekledik
    var days: List<String> = mutableListOf(),  // Başlangıç değeri ekledik
    var selectedDays: List<String> = emptyList() // Yeni eklenen alan

)
