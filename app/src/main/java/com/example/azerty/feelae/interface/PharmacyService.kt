package com.example.azerty.feelae.`interface`

import com.example.azerty.feelae.model.Pharmacy
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PharmacyService {
    @GET("/maps/api/place/nearbysearch/json")
    fun listPharmacy(@Query("key") apiKey: String,
                     @Query("location") location: String,
                     @Query("radius") radius: String,
                     @Query("type") type: String): Call<Pharmacy>
}
