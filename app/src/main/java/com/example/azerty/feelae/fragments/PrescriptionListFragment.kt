package com.example.azerty.feelae.fragments

import android.net.Uri
import android.os.Bundle
import android.renderscript.Sampler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beust.klaxon.Klaxon
import com.example.azerty.feelae.R
import com.example.azerty.feelae.adapter.PrescriptionAdapter
import com.example.azerty.feelae.model.Prescription
import com.example.elgrim.seasonal.http.APIController
import com.example.elgrim.seasonal.http.ServiceVolley
import kotlinx.android.synthetic.main.prescription_list_fragment.*
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import org.json.JSONObject
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class PrescriptionListFragment: Fragment() {

    var prescriptionsData : List<Prescription>? = null
    var prescriptions: ArrayList<Prescription>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.prescription_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPrescriptions()
    }

    private fun getPrescriptions() {
        val service = ServiceVolley()
        val apiController = APIController(service)
        apiController.get("/bdd.json",null) { response ->

            if (response != null) {
                prescriptionsData = Klaxon().parseArray(response.toString())
                prescriptions = prescriptionsData as ArrayList<Prescription>
            }

            prescription_list.layoutManager = LinearLayoutManager(context)
            val itemAdapter = FastItemAdapter<PrescriptionAdapter>()
            itemAdapter.add(prescriptions?.map { PrescriptionAdapter(it) })
            prescription_list.adapter = itemAdapter
        }
    }
}