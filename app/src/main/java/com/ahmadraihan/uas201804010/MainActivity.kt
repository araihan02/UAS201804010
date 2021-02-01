package com.ahmadraihan.uas201804010

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtLogout: TextView = findViewById(R.id.txtlogout)
        val txtUser: TextView = findViewById(R.id.txtuser)
        val txtCake: TextView = findViewById(R.id.txtcake)
        val txtIcecream: TextView = findViewById(R.id.txticecream)

        val savedLogin = getSharedPreferences("Login", MODE_PRIVATE)
        val editSavedLogin = savedLogin.edit()
        txtLogout.setOnClickListener {
            editSavedLogin.putString("Email", null)
            editSavedLogin.putString("Password", null)
            editSavedLogin.putString("Status", "Off")
            editSavedLogin.commit()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        txtUser.setOnClickListener{
            val intent = Intent(this, RvDataUser::class.java)
            startActivity(intent)
        }

        txtCake.setOnClickListener{
            val intent = Intent(this, CakeMain::class.java)
            startActivity(intent)
        }

        txtIcecream.setOnClickListener{
            val intent = Intent(this, IcecreamMain::class.java)
            startActivity(intent)
        }
    }

}