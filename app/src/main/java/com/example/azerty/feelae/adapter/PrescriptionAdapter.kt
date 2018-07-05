package com.example.azerty.feelae.adapter

import android.content.Context
import android.content.res.Resources
import android.support.constraint.R.id.parent
import android.view.View
import com.mikepenz.fastadapter.items.AbstractItem
import com.example.azerty.feelae.R
import com.example.azerty.feelae.model.Prescription
import com.example.azerty.feelae.utils.format
import com.mikepenz.fastadapter.FastAdapter
import kotlinx.android.synthetic.main.prescription_list_item.view.*

class PrescriptionAdapter (val item: Prescription, context : Context): AbstractItem<PrescriptionAdapter, PrescriptionAdapter.PrescriptionViewHolder>()  {
    val context  = context
    override fun getType(): Int {
        return R.id.prescription_list
    }

    override fun getViewHolder(v: View?): PrescriptionViewHolder {
       return PrescriptionViewHolder(v,context)
    }

    override fun getLayoutRes(): Int {
        return R.layout.prescription_list_item
    }


    class PrescriptionViewHolder(itemView: View?, context: Context): FastAdapter.ViewHolder<PrescriptionAdapter>(itemView) {
        val context = context
        override fun unbindView(item: PrescriptionAdapter?) {
            itemView.precription_name_docteur.text = null
            itemView.prescription_status.text = null
            itemView.prescription_give_at.text = null
        }

        override fun bindView(item: PrescriptionAdapter?, payloads: MutableList<Any>?) {
            val prescription = item?.item
            itemView.precription_name_docteur.text = prescription?.practitioner_name
            if (prescription?.finish == 0) {
                itemView.prescription_status.text = "En cours"
            }else {
                itemView.prescription_status.text = "Fini"
                itemView.prescription_status.setBackgroundColor( context.getColor(R.color.title_shadow))
            }
            itemView.prescription_give_at.text = format(prescription?.start_at)
        }

    }
}