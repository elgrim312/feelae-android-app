package com.example.azerty.feelae.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beust.klaxon.Klaxon
import com.example.azerty.feelae.MapsActivity
import com.example.azerty.feelae.R
import com.example.azerty.feelae.TraitementDetailActivity
import com.example.azerty.feelae.adapter.PrescriptionAdapter
import com.example.azerty.feelae.model.Prescription
import com.example.elgrim.seasonal.http.APIController
import com.example.elgrim.seasonal.http.ServiceVolley
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.prescription_list_fragment.*
import kotlin.collections.ArrayList

class PrescriptionListFragment: Fragment() {
    companion object {
        fun newInstance(): PrescriptionListFragment = PrescriptionListFragment()
    }
    var prescriptionsData : List<Prescription>? = null
    var prescriptions: ArrayList<Prescription>? = null
    val itemAdapter = FastItemAdapter<PrescriptionAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.prescription_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prescription_list.layoutManager = LinearLayoutManager(context)
        getPrescriptions()

        itemAdapter.withOnClickListener ({ _, _, item, _->
            val intent = Intent(this.context, TraitementDetailActivity::class.java)
            intent.putExtra("traitement_EXTRA", item.item)
            startActivity(intent)
            true
        })

        prescription_list_button.setOnClickListener {
            val intent = Intent(activity, MapsActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        }
    }

    private fun getPrescriptions() {
        val service = ServiceVolley()
        val apiController = APIController(service)
        apiController.get("/bdd.json",null) { response ->
            if (response != null) {
                prescriptionsData = Klaxon().parseArray(response.toString())
                prescriptions = prescriptionsData as ArrayList<Prescription>
                Log.d("DEBUG", prescriptionsData.toString())
                itemAdapter.notifyItemRangeRemoved(0, prescriptions!!.size)
                itemAdapter.add(prescriptions?.map { PrescriptionAdapter(it, this.context!!) })
                prescription_list.adapter = itemAdapter
            }
        }
    }
}