package com.example.weather

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class CropListAdapter (private val context: Activity, private val title1: Array<String>,private val title2: Array<String>, private val imgid1: Array<Int>, private val imgid2: Array<Int>)
    : ArrayAdapter<String>(context, R.layout.customlistview_layout, title1) {
    private lateinit var cropImage1: ImageView
    private lateinit var cropImage2: ImageView
    private lateinit var cropName1: TextView
    private lateinit var cropName2: TextView
    private lateinit var cropCard1: CardView
    private lateinit var cropCard2: CardView
        override fun getView(position: Int, view: View?, parent: ViewGroup): View {
            val inflater = context.layoutInflater
            val rowView = inflater.inflate(R.layout.customlistview_layout, null, true)


            cropImage1= rowView.findViewById(R.id.cropImage)
            cropImage2= rowView.findViewById(R.id.cropImage2)
            cropName1= rowView.findViewById(R.id.cropName)
            cropName2= rowView.findViewById(R.id.cropName2)
            cropCard1= rowView.findViewById(R.id.cropCard1)
            cropCard2= rowView.findViewById(R.id.cropCard2)


            cropName1.text = title1[position]
            cropName2.text = title2[position]
            cropImage1.setImageResource(imgid1[position])
            cropImage2.setImageResource(imgid2[position])

            cropCard1.setOnClickListener {
                val intent= Intent(this.context,CropDetailActivity::class.java)
                Log.d("card1",title1[position].toString())
                intent.putExtra("cropName",title1[position])
                this.getContext().startActivity(intent)
            }
            cropCard2.setOnClickListener {
                Log.d("card2",title2[position].toString())

                val intent= Intent(this.context,CropDetailActivity::class.java)
                intent.putExtra("cropName",title2[position])
                this.getContext().startActivity(intent)
            }

            return rowView
        }
    }