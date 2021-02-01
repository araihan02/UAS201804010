package com.ahmadraihan.uas201804010

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RvDataCake : AppCompatActivity() {
    private lateinit var rv_tampilanku: RecyclerView
    lateinit var cakeDBHelper: DBHelper
    private  var list: ArrayList<DataModelCake> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rvdata_cake)
        rv_tampilanku = findViewById(R.id.rv_tampilkan)
        rv_tampilanku.setHasFixedSize(true)
        cakeDBHelper = DBHelper(this)
        list.addAll(cakeDBHelper.fullDataCake())
        rv_tampilanku.layoutManager = LinearLayoutManager(this)
        var cardData = RvDataCakeAdapter(list)
        rv_tampilanku.adapter = cardData
    }
}