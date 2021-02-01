package com.ahmadraihan.uas201804010

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class IcecreamMain : AppCompatActivity() {
    lateinit var icecreamDBHelper: DBHelper
    lateinit var inputid: EditText
    lateinit var inputname: EditText
    lateinit var inputprice: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.icecream_main)
        inputid = findViewById(R.id.input_id)
        inputname = findViewById(R.id.input_name)
        inputprice = findViewById(R.id.input_price)
        icecreamDBHelper = DBHelper(this)
    }
    fun addData(v: View){
        var idin = inputid.text.toString()
        var namein = inputname.text.toString()
        var pricein = inputprice.text.toString()
        icecreamDBHelper.InsertIcecream(idin, namein, pricein)
        inputid.setText("")
        inputname.setText("")
        inputprice.setText("")

    }
    fun showAll(v: View){
        var pindah = Intent(this, RvDataIcecream ::class.java)
        startActivity(pindah)
    }
}