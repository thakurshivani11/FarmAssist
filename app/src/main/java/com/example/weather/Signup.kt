package com.example.weather

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.password
import com.google.firebase.auth.ktx.auth


class Signup :AppCompatActivity(){
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()!!.hide(); // hide the title bar
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up)
        loginText.setOnClickListener{
            startActivity(Intent(this@Signup,LoginActivity::class.java))
        }
        auth=Firebase.auth
    buttonSignUp.setOnClickListener{
        when{
            TextUtils.isEmpty(user.text.toString().trim{ it <= ' '})->{
                Toast.makeText(this@Signup,
                    "please enter your name!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            TextUtils.isEmpty(email.text.toString().trim{ it <= ' '})->{
                Toast.makeText(this@Signup,
                    "please enter mail",
                    Toast.LENGTH_SHORT
                ).show()
            }

            TextUtils.isEmpty(password.text.toString().trim{ it <= ' '})->{
                Toast.makeText(this@Signup,
                    "please enter password",
                    Toast.LENGTH_SHORT
                ).show()
        }
            else ->{

                val email:String=email.text.toString().trim{ it <=' '}
                val password:String=password.text.toString().trim{ it <=' '}
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(
                        {
                            task ->
                            if(task.isSuccessful){
                                val firebaseUser: FirebaseUser =task.result!!.user!!
                                Toast.makeText(
                                    this@Signup,
                                    "you are registered!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val User = auth.currentUser
                                val profileUpdates: UserProfileChangeRequest =
                                    UserProfileChangeRequest.Builder().setDisplayName(user.text.toString()).build()
                                User!!.updateProfile(profileUpdates);

                             val intent=
                                 Intent(this@Signup,LoginActivity::class.java)
                                intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id",firebaseUser.uid)
                                intent.putExtra("email_id",email)
                                startActivity(intent)
                                finish()
                            }
                            else{
                                Toast.makeText(
                                    this@Signup,task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
            }
        }
    }
    }
}