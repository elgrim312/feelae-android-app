package com.example.azerty.feelae.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Traitement (
   val symptome : ArrayList<String>,
   val traitement : ArrayList<String>
): Parcelable