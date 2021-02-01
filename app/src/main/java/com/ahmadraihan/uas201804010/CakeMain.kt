package com.ahmadraihan.uas201804010

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class CakeMain : AppCompatActivity() {
    lateinit var cakeDBHelper: DBHelper
    lateinit var inputid: EditText
    lateinit var inputname: EditText
    lateinit var inputprice: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cake_main)
        inputid = findViewById(R.id.input_id)
        inputname = findViewById(R.id.input_name)
        inputprice = findViewById(R.id.input_price)
        cakeDBHelper = DBHelper(this)
    }
    fun addData(v: View){
        var idin = inputid.text.toString()
        var namein = inputname.text.toString()
        var pricein = inputprice.text.toString()
        cakeDBHelper.InsertCake(idin, namein, pricein)
        inputid.setText("")
        inputname.setText("")
        inputprice.setText("")

    }
    fun showAll(v: View){
        var pindah = Intent(this, RvDataCake ::class.java)
        startActivity(pindah)
    }
}