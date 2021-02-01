package com.ahmadraihan.uas201804010

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RvDataIcecream : AppCompatActivity() {
    private lateinit var rv_tampilanku: RecyclerView
    lateinit var icecreamDBHelper: DBHelper
    private  var list: ArrayList<DataModelIcecream> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rvdata_icecream)
        rv_tampilanku = findViewById(R.id.rv_tampilkan)
        rv_tampilanku.setHasFixedSize(true)
        icecreamDBHelper = DBHelper(this)
        list.addAll(icecreamDBHelper.fullDataIcecream())
        rv_tampilanku.layoutManager = LinearLayoutManager(this)
        var cardData = RvDataIcecreamAdapter(list)
        rv_tampilanku.adapter = cardData
    }
}