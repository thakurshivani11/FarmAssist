package com.example.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView

class CropDetailActivity : AppCompatActivity() {
    private lateinit var cropName:TextView
    private lateinit var cropSeason:TextView
    private lateinit var cropImage:ImageView
    private lateinit var cropDesc:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()!!.hide(); // hide the title bar
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_crop_detail)
        cropName=findViewById(R.id.cropName)
        cropSeason=findViewById(R.id.cropSeason)
        cropImage=findViewById(R.id.cropImage)
        cropDesc=findViewById(R.id.cropDesc)
        val intent=intent
        val name=intent.getStringExtra("cropName")
        Log.d("cropname",name.toString())
        val cropData:Crop=CropSearch.getCropDetail(name.toString()) as Crop
        Log.d("cropdata",cropData.toString())

        cropName.text = cropData.cropName
        cropSeason.text = "Sesaon: ${cropData.cropSeason}"
        cropImage.setImageResource(cropData.cropImage!!)
        cropDesc.text = cropData.cropDesc
    }
}