package com.example.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class CropFragment : Fragment() {
    private lateinit var croplistView:ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.cropfragment_layout, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//  val view=this.view
        croplistView=view.findViewById(R.id.cropListView)
        val cropName1= arrayOf("Brinjal","Ladyfinger","Banana",)
        val cropName2=arrayOf("Gourd","Ridged Gourd","Wheat")
        val cropImageId1 = arrayOf<Int>(
            R.drawable.brinjal,R.drawable.ladyfinger,R.drawable.banana

//            R.drawable.c_image,R.drawable.cpp_image,R.drawable.java_image,
//            R.drawable.net_image,R.drawable.kotlin_image,R.drawable.ruby_image,
//            R.drawable.rails_image,R.drawable.python_image,R.drawable.js_image,
//            R.drawable.php_image,R.drawable.ajax_image,R.drawable.python_image,
//            R.drawable.hadoop_image
        )
        val cropImageId2 = arrayOf<Int>(
            R.drawable.gourd,R.drawable.rigded,R.drawable.wheat
//            R.drawable.c_image,R.drawable.cpp_image,R.drawable.java_image,
//            R.drawable.net_image,R.drawable.kotlin_image,R.drawable.ruby_image,
//            R.drawable.rails_image,R.drawable.python_image,R.drawable.js_image,
//            R.drawable.php_image,R.drawable.ajax_image,R.drawable.python_image,
//            R.drawable.hadoop_image
        )
        val cropAdapter = CropListAdapter(this.requireActivity(),cropName1,cropName2,cropImageId1,cropImageId2)
        croplistView.adapter = cropAdapter

    }
}