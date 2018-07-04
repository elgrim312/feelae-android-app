package com.example.azerty.feelae.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mikepenz.fastadapter.items.AbstractItem
import com.example.azerty.feelae.R
import com.example.azerty.feelae.model.Prescription
import com.example.azerty.feelae.utils.format
import com.mikepenz.fastadapter.FastAdapter
import kotlinx.android.synthetic.main.prescription_list_item.view.*

class PrescriptionAdapter (val item: Prescription): AbstractItem<PrescriptionAdapter, PrescriptionAdapter.PrescriptionViewHolder>()  {
    override fun getType(): Int {
        return R.id.prescription_at
    }

    override fun getViewHolder(v: View?): PrescriptionViewHolder {
       return PrescriptionViewHolder(v)
    }

    override fun getLayoutRes(): Int {
        return R.layout.prescription_list_item
    }

    class PrescriptionViewHolder(itemView: View?): FastAdapter.ViewHolder<PrescriptionAdapter>(itemView) {
        override fun unbindView(item: PrescriptionAdapter?) {
            itemView.precription_name.text = null
            itemView.prescription_at.text = null
            //itemView.prescription_occurence_type.text = null
            itemView.prescription_occurence.text = null
            itemView.prescription_dosage.text = null

        }

        override fun bindView(item: PrescriptionAdapter?, payloads: MutableList<Any>?) {
            val prescription = item?.item
            itemView.precription_name.text = prescription?.name
            itemView.prescription_at.text = format(prescription?.giveAt)
            //itemView.prescription_occurence_type.text = prescription?.occurenceType
            itemView.prescription_occurence.text = prescription?.occurence.toString()
            itemView.prescription_dosage.text = prescription?.dosage
        }

    }
}