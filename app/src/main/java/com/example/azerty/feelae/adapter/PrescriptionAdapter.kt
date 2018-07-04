package com.example.azerty.feelae.adapter

import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.View
import com.mikepenz.fastadapter.items.AbstractItem
import com.example.azerty.feelae.R
import com.example.azerty.feelae.model.Prescription
import com.example.azerty.feelae.utils.format
import com.mikepenz.fastadapter.FastAdapter
import kotlinx.android.synthetic.main.prescription_list_item.view.*

class PrescriptionAdapter (val item: Prescription): AbstractItem<PrescriptionAdapter, PrescriptionAdapter.PrescriptionViewHolder>()  {
    override fun getType(): Int {
        return R.id.prescription_list
    }

    override fun getViewHolder(v: View?): PrescriptionViewHolder {
       return PrescriptionViewHolder(v)
    }

    override fun getLayoutRes(): Int {
        return R.layout.prescription_list_item
    }

    class PrescriptionViewHolder(itemView: View?): FastAdapter.ViewHolder<PrescriptionAdapter>(itemView) {
        override fun unbindView(item: PrescriptionAdapter?) {
            itemView.precription_name_docteur.text = null
            itemView.prescription_status.text = null
            itemView.prescription_give_at.text = null
            itemView.prescription_medicament_name.text = null
            itemView.prescription_occurence.text = null

        }

        override fun bindView(item: PrescriptionAdapter?, payloads: MutableList<Any>?) {
            val prescription = item?.item
            itemView.precription_name_docteur.text = prescription?.practitioner_name
            if (prescription?.finish == 0) {

                itemView.prescription_status.text = "En cours"


            }else {
                itemView.prescription_status.text = "Fin"
                DrawableCompat.setTint(itemView.prescription_status.background, ContextCompat.getColor(itemView.context, R.color.status_end));
            }
            itemView.prescription_give_at.text = format(prescription?.start_at)
            itemView.prescription_medicament_name.text = prescription?.prescription_name
            itemView.prescription_occurence.text = prescription?.duration_traitement
        }

    }
}