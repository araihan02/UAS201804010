package com.ahmadraihan.uas201804010

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class UpdateCake : AppCompatActivity() {
    lateinit var cakeDBHelper: DBHelper
    lateinit var inputid: EditText
    lateinit var inputname: EditText
    lateinit var inputprice: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_cake)
        inputid = findViewById(R.id.input_idu)
        inputname = findViewById(R.id.input_nameu)
        inputprice = findViewById(R.id.input_priceu)
        cakeDBHelper = DBHelper(this)
        val bundle = intent.extras
        if (bundle!=null){
            inputid.setText(bundle.getString("idk"))
            inputname.setText(bundle.getString("namek"))
            inputprice.setText(bundle.getString("pricek"))

        }
    }
    fun updateData(v: View){
        var idin = inputid.text.toString()
        var namein = inputname.text.toString()
        var pricein = inputprice.text.toString()
        cakeDBHelper.updateCake(idin, namein, pricein)
        var pindah = Intent(this, RvDataCake::class.java)
        startActivity(pindah)
    }
    fun cancelData(v: View){
        var pindah = Intent(this, RvDataCake::class.java)
        startActivity(pindah)
    }
}