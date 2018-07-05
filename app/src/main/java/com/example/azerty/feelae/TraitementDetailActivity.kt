package com.example.azerty.feelae

import android.app.Activity
import android.app.Presentation
import android.os.Bundle
import android.util.Log
import com.example.azerty.feelae.model.Prescription
import com.example.azerty.feelae.utils.concat
import com.hendraanggrian.pikasso.circle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_traitement_detail.*

class TraitementDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val traitement = intent.getParcelableExtra<Prescription>("traitement_EXTRA")
        setContentView(R.layout.activity_traitement_detail)

        //todo include in json
        Picasso.get().load("http://phyt-equilibre.www6.produweb.be/img/cms/Th%C3%A9rapeutes%20s%C3%A9pcialis%C3%A9s.jpg").circle().into(traitement_detail_img_doc)
        traitement_detail_title.text = "Ordonance du ${traitement.start_at}"
        traitement_detail_docteur_name.text = traitement.practitioner_name
        traitement_detail_start_at.text = traitement.start_at

        traitement_detail_symptome.text = concat(traitement.symptome)
        traitement_detail_traitement.text = concat(traitement.traitement)
        traitement_detail_side_effect.text = concat(traitement.side_effect)
        traitement_detail_indication.text = concat(traitement.indication)


    }
}
