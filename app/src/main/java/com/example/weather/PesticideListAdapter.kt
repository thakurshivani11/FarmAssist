package com.example.weather

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class PesticideListAdapter (private val context: Activity, private val title1: Array<String>,private val title2: Array<String>, private val imgid1: Array<Int>, private val imgid2: Array<Int>)
    : ArrayAdapter<String>(context, R.layout.pesticidefragment_layout, title1) {
    private lateinit var pesticideImage1: ImageView
    private lateinit var pesticideImage2: ImageView
    private lateinit var pesticideName1: TextView
    private lateinit var pesticideName2: TextView
    private lateinit var pesticideCard1: CardView
    private lateinit var pesticideCard2: CardView
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custompesticidelistview_layout, null, true)


        pesticideImage1=rowView.findViewById(R.id.pesticideImage)
        pesticideImage2=rowView.findViewById(R.id.pesticideImage2)
        pesticideImage2=rowView.findViewById(R.id.pesticideImage2)
        pesticideName1= rowView.findViewById(R.id.pesticideName)
        pesticideName2= rowView.findViewById(R.id.pesticideName2)
        pesticideCard1= rowView.findViewById(R.id.pesticideCard1)
        pesticideCard2= rowView.findViewById(R.id.pesticideCard1)


        pesticideName1.text = title1[position]
        pesticideName2.text = title2[position]
        pesticideImage1.setImageResource(imgid1[position])
        pesticideImage2.setImageResource(imgid2[position])


        return rowView
    }
}