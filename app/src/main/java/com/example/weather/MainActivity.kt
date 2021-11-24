package com.example.weather

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
private lateinit var auth:FirebaseAuth
var userId=""
    var emailId=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()!!.hide(); // hide the title bar
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main)
//        val userId =intent.getStringExtra("user_id")
//        val emailId=intent.getStringExtra("email_id")
        auth=Firebase.auth
        val user=auth.currentUser

        if (user == null) {
            Log.d("user",user.toString())
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        user?.let{
            userId=user.displayName.toString()
            emailId=user.email.toString()
        }


        tv_userid.text="user id:: $userId"
        tv_emailid.text="email id :: $emailId"

        logoutbutton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity,LoginActivity::class.java))
            finish()
        }
        LoanCard.setOnClickListener{
            val loanurl=Intent(android.content.Intent.ACTION_VIEW)
            loanurl.data= Uri.parse("https://sbi.co.in/web/agri-rural/agriculture-banking/crop-loan/kisan-credit-card")
            startActivity(loanurl)

        }
        todocardview.setOnClickListener{
            val Intent =Intent(this@MainActivity,TodoActivity::class.java)
            startActivity(Intent)

        }
        weatherCard.setOnClickListener{
            val intent= Intent(this@MainActivity,Weather::class.java)

            startActivity(intent)

        }
        cropCard.setOnClickListener{
            val intent= Intent(this@MainActivity,Agriculture::class.java)

            startActivity(intent)

        }
    }
}