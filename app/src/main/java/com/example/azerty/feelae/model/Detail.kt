package com.example.azerty.feelae.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Detail (
        val day : Int,
        val medicament : String,
        val occurence : Int
) : Parcelable