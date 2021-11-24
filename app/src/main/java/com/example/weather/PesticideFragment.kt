package com.example.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment

class PesticideFragment : Fragment() {
    private lateinit var pesticidelistView: ListView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.pesticidefragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // val view=this.view
        pesticidelistView=view.findViewById(R.id.pesticideListView)
        val pesticideName1= arrayOf("oberon")
        val pesticideName2=arrayOf("sultaf")
        val pesticideImageId1 = arrayOf<Int>(
            R.drawable.oberon
//            R.drawable.c_image,R.drawable.cpp_image,R.drawable.java_image,
//            R.drawable.net_image,R.drawable.kotlin_image,R.drawable.ruby_image,
//            R.drawable.rails_image,R.drawable.python_image,R.drawable.js_image,
//            R.drawable.php_image,R.drawable.ajax_image,R.drawable.python_image,
//            R.drawable.hadoop_image
        )
        val pesticideImageId2 = arrayOf<Int>(
            R.drawable.sultaf
//            R.drawable.c_image,R.drawable.cpp_image,R.drawable.java_image,
//            R.drawable.net_image,R.drawable.kotlin_image,R.drawable.ruby_image,
//            R.drawable.rails_image,R.drawable.python_image,R.drawable.js_image,
//            R.drawable.php_image,R.drawable.ajax_image,R.drawable.python_image,
//            R.drawable.hadoop_image
        )
        val pesticideAdapter = PesticideListAdapter(this.requireActivity(),pesticideName1,pesticideName2,pesticideImageId1,pesticideImageId2)
        pesticidelistView.adapter = pesticideAdapter
    }
}