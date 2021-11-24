package com.example.weather

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_login.*

import kotlinx.android.synthetic.main.activity_sign_up.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()!!.hide(); // hide the title bar
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login)
        signUpText.setOnClickListener{
            startActivity(Intent(this@LoginActivity,Signup::class.java))
        }
        buttonLogin.setOnClickListener{
            when{
                TextUtils.isEmpty(loginemail.text.toString().trim{ it <= ' '})->{
                    Toast.makeText(this@LoginActivity,
                        "please enter mail",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(loginpassword.text.toString().trim{ it <= ' '})->{
                    Toast.makeText(this@LoginActivity,
                        "please enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else ->{
                    val email:String=loginemail.text.toString().trim{ it <=' '}
                    val password:String=loginpassword.text.toString().trim{ it <=' '}
                    //login using fireauth
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(
                            {task ->
                                if(task.isSuccessful){

                                    Toast.makeText(
                                        this@LoginActivity,
                                        "you are logged in Successfully!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent=
                                        Intent(this@LoginActivity,MainActivity::class.java)
                                    intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra(
                                        "user_id",
                                        FirebaseAuth.getInstance().currentUser!!.uid
                                    )
                                    intent.putExtra("email_id",email)
                                    startActivity(intent)
                                    finish()
                                }
                                else{
                                    Toast.makeText(
                                        this@LoginActivity,task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                }
            }
        }
    }
    }