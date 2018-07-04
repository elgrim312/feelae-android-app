package com.example.azerty.feelae.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class Prescription (
        val practitioner_name: String,
        val malady_name: String,
        val start_at: String,
        val end_at: String,
        val finish: Int,
        val prescription_name: String,
        val duration_traitement: String,
        val side_effect: ArrayList<String>,
        val indication: ArrayList<String>
): Parcelable
