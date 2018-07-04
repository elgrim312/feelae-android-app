package com.example.azerty.feelae.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Prescription (
        val duration: String,
        val dosage: String,
        val occurence: Int,
        val giveAt: String,
        val name: String,
        val finish: Int,
        val sideEffect: ArrayList<String>
       // val occurenceType: String
): Parcelable
