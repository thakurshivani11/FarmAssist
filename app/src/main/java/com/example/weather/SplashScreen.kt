package com.example.weather


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AnimationUtils.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreen : AppCompatActivity() {
    public  var locationRequest:LocationRequest=LocationRequest()
    public val REQUEST_CHECK_SETTINGS = 0x1
    private lateinit var auth:FirebaseAuth
    lateinit var mfusedlocation:FusedLocationProviderClient
    private var myResquestCode=1010
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        auth=Firebase.auth
        createLocationRequest(this)


        //shared preference

        //animation
        val topanimation = loadAnimation(this, R.anim.top_animation)
        val middlleanimation = loadAnimation(this, R.anim.middle_animation)
        val bottomanimation = loadAnimation(this, R.anim.bottom_animation)
        //----------
        first.startAnimation(topanimation)
        second.startAnimation(topanimation)
        third.startAnimation(topanimation)
        fourth.startAnimation(topanimation)
        fifth.startAnimation(topanimation)
        sixth.startAnimation(topanimation)
        //--------
        centername.startAnimation(middlleanimation)
        //-----------
        slogan.startAnimation(bottomanimation)
        //location
        mfusedlocation= LocationServices.getFusedLocationProviderClient(this)

        getLastLocation()
    }

//    1. location permission --> deny
//    2. location denied through settings
//    3. gps off
//    4. permission le lo

    public fun createLocationRequest(activity: Activity) {

        locationRequest = LocationRequest.create()?.apply {
            interval = 1000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }!!
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client: SettingsClient = LocationServices.getSettingsClient(activity)

        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
        task.addOnSuccessListener { locationSettingsResponse ->
            // All location settings are satisfied. The client can initialize
            // location requests here.
            // ...\
            // locationHandler.onResult(locationSettingsResponse.locationSettingsStates.isGpsUsable)
            //makeToast(activity,locationSettingsResponse.locationSettingsStates.isGpsUsable.toString()).show()

        }
        if(task.isSuccessful){

        }


        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(
                        activity,
                        REQUEST_CHECK_SETTINGS
                    )
                    // locationHandler.onError("Error")
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("LocationCordinates",Context.MODE_PRIVATE)

        if(CheckPermission()) {
            if(LocationEnable()){
                mfusedlocation.lastLocation.addOnCompleteListener{
                    task->
                    var location:Location?=task.result
                    Log.d("location",location.toString())
                    if(location==null)
                    {
                        NewLocation()
                    }else {
                        auth = Firebase.auth
                        val user = auth.currentUser

                        if (user != null) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Handler(Looper.getMainLooper()).postDelayed({
                                val intent = Intent(this, LoginActivity::class.java)
                                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                                editor.putString("lat", location.latitude.toString())
                                editor.putString("long", location.longitude.toString())
                                editor.apply()
                                editor.commit()
//                            intent.putExtra("lat",location.latitude.toString())
//                            intent.putExtra("long",location.longitude.toString())
                                startActivity(intent)
                                finish()
                            }, 2000)

                        }
                    }
                }
            }else{
                Toast.makeText(this,"Please Turn on your GPS location",Toast.LENGTH_LONG).show()


            }
        }else{
            RequestPermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun NewLocation() {
        var locationRequest=LocationRequest()
        locationRequest.priority=LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval=0
        locationRequest.fastestInterval=0
        locationRequest.numUpdates=1
        mfusedlocation=LocationServices.getFusedLocationProviderClient(this)
        mfusedlocation.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper())
    }
    private val locationCallback=object:LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation:Location=p0.lastLocation
        }
    }

    private fun LocationEnable(): Boolean {
        var locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun RequestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION),myResquestCode)
    }

    private fun CheckPermission(): Boolean {
        if(
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED
        )
        {
            return true
        }
        RequestPermission()
        return false
    }



    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        if(requestCode==myResquestCode)
        {
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                getLastLocation()
            }
        }
    }
}

