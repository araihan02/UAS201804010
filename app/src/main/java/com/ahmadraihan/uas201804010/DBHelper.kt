package com.ahmadraihan.uas201804010

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import java.sql.SQLException
import java.sql.SQLTimeoutException

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        val DATABASE_NAME = "myaps.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_USER = "CREATE TABLE " + DBInfo.UserTable.TABLE_NAME + "("+DBInfo.UserTable.COL_EMAIL+" VARCHAR(200) PRIMARY KEY, " + DBInfo.UserTable.COL_PASS + " TEXT, " + DBInfo.UserTable.COL_FULLNAME + " TEXT, " + DBInfo.UserTable.COL_JENKAL + " VARCHAR(200), " + DBInfo.UserTable.COL_ALAMAT + " TEXT)"
        private val SQL_CREATE_CAKE = "CREATE TABLE " + DBInfo.CakeTable.TABLE_NAME + "("+DBInfo.CakeTable.COL_ID+" VARCHAR(200) PRIMARY KEY, " + DBInfo.CakeTable.COL_NAME + " TEXT, " + DBInfo.CakeTable.COL_PRICE + " TEXT)"
        private val SQL_CREATE_ICECREAM = "CREATE TABLE " + DBInfo.IcecreamTable.TABLE_NAME + "("+DBInfo.IcecreamTable.COL_ID+" VARCHAR(200) PRIMARY KEY, " + DBInfo.IcecreamTable.COL_NAME + " TEXT, " + DBInfo.IcecreamTable.COL_PRICE + " TEXT)"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBInfo.UserTable.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_USER)
        db?.execSQL(SQL_CREATE_CAKE)
        db?.execSQL(SQL_CREATE_ICECREAM)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    @Throws(SQLiteConstraintException::class)

    //insert data
    fun RegisterUser(emailin: String, passin:String, fullnamein: String, jenkalin: String, alamatin: String) {
        val db = writableDatabase
        val namatable = DBInfo.UserTable.TABLE_NAME
        val emailt = DBInfo.UserTable.COL_EMAIL
        val passt = DBInfo.UserTable.COL_PASS
        val fullnamet = DBInfo.UserTable.COL_FULLNAME
        val jenkalt = DBInfo.UserTable.COL_JENKAL
        val alamatt = DBInfo.UserTable.COL_ALAMAT
        var sql = "INSERT INTO " + namatable + " (" + emailt + ", " + passt + ", " + fullnamet + ", " + jenkalt + ", " + alamatt + ") VALUES('" + emailin + "', '" + passin + "', '" + fullnamein + "', '" + jenkalin + "', '" + alamatin + "')"
        db.execSQL(sql)
    }
    fun InsertCake(idin: String, namein:String, pricein: String) {
        val db = writableDatabase
        val namatable = DBInfo.CakeTable.TABLE_NAME
        val idt = DBInfo.CakeTable.COL_ID
        val namet = DBInfo.CakeTable.COL_NAME
        val pricet = DBInfo.CakeTable.COL_PRICE
        var sql = "INSERT INTO " + namatable + " (" + idt + ", " + namet + ", " + pricet + ") VALUES('" + idin + "', '" + namein + "', '" + pricein + "')"
        db.execSQL(sql)
    }
    fun InsertIcecream(idin: String, namein:String, pricein: String) {
        val db = writableDatabase
        val namatable = DBInfo.IcecreamTable.TABLE_NAME
        val idt = DBInfo.IcecreamTable.COL_ID
        val namet = DBInfo.IcecreamTable.COL_NAME
        val pricet = DBInfo.IcecreamTable.COL_PRICE
        var sql = "INSERT INTO " + namatable + " (" + idt + ", " + namet + ", " + pricet + ") VALUES('" + idin + "', '" + namein + "', '" + pricein + "')"
        db.execSQL(sql)
    }
    fun cekUser(emailin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBInfo.UserTable.TABLE_NAME + " WHERE " + DBInfo.UserTable.COL_EMAIL + "=='" + emailin +"'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }
    fun cekLogin(emailin: String, passin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBInfo.UserTable.TABLE_NAME + " WHERE " + DBInfo.UserTable.COL_EMAIL + "=='" + emailin +"' AND " + DBInfo.UserTable.COL_PASS + "=='" + passin + "'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }

    //tampil data
    fun fullDataUser():ArrayList<DataModelUser> {
        val users = arrayListOf<DataModelUser>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM "+DBInfo.UserTable.TABLE_NAME, null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return ArrayList()
        }
        var emailt: String
        var passt: String
        var fullnamet: String
        var alamatt: String
        var jkt: String
        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast==false){
                emailt = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_EMAIL))
                passt = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_PASS))
                fullnamet = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_FULLNAME))
                alamatt = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_ALAMAT))
                jkt = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JENKAL))

                users.add(DataModelUser(emailt, passt, fullnamet, alamatt, jkt))
                cursor.moveToNext()
            }
        }
        return  users
    }
    fun fullDataCake():ArrayList<DataModelCake> {
        val cakes = arrayListOf<DataModelCake>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM "+DBInfo.CakeTable.TABLE_NAME, null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_CAKE)
            return ArrayList()
        }
        var idt: String
        var namet: String
        var pricet: String
        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast==false){
                idt = cursor.getString(cursor.getColumnIndex(DBInfo.CakeTable.COL_ID))
                namet = cursor.getString(cursor.getColumnIndex(DBInfo.CakeTable.COL_NAME))
                pricet = cursor.getString(cursor.getColumnIndex(DBInfo.CakeTable.COL_PRICE))

                cakes.add(DataModelCake(idt, namet, pricet))
                cursor.moveToNext()
            }
        }
        return  cakes
    }
    fun fullDataIcecream():ArrayList<DataModelIcecream> {
        val icecream = arrayListOf<DataModelIcecream>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM "+DBInfo.IcecreamTable.TABLE_NAME, null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_ICECREAM)
            return ArrayList()
        }
        var idt: String
        var namet: String
        var pricet: String
        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast==false){
                idt = cursor.getString(cursor.getColumnIndex(DBInfo.IcecreamTable.COL_ID))
                namet = cursor.getString(cursor.getColumnIndex(DBInfo.IcecreamTable.COL_NAME))
                pricet = cursor.getString(cursor.getColumnIndex(DBInfo.IcecreamTable.COL_PRICE))

                icecream.add( DataModelIcecream(idt, namet, pricet))
                cursor.moveToNext()
            }
        }
        return  icecream
    }

    //delete data
    fun deleteUser(emailin: String){
        val db = writableDatabase
        val namatablet = DBInfo.UserTable.TABLE_NAME
        val emailt = DBInfo.UserTable.COL_EMAIL
        val sql = "DELETE FROM " +namatablet+ " WHERE "+emailt+"='"+emailin+"'"
        db.execSQL(sql)
    }
    fun deleteCake(idin: String){
        val db = writableDatabase
        val namatablet = DBInfo.CakeTable.TABLE_NAME
        val idt = DBInfo.CakeTable.COL_ID
        val sql = "DELETE FROM " +namatablet+ " WHERE "+idt+"='"+idin+"'"
        db.execSQL(sql)
    }
    fun deleteIcecream(idin: String){
        val db = writableDatabase
        val namatablet = DBInfo.IcecreamTable.TABLE_NAME
        val idt = DBInfo.IcecreamTable.COL_ID
        val sql = "DELETE FROM " +namatablet+ " WHERE "+idt+"='"+idin+"'"
        db.execSQL(sql)
    }

    //update data
    fun updateUser(emailin: String, passin:String, fullnamein: String, jenkalin: String, alamatin: String){
        val db = writableDatabase
        val namatablet = DBInfo.UserTable.TABLE_NAME
        val emailt = DBInfo.UserTable.COL_EMAIL
        val passt = DBInfo.UserTable.COL_PASS
        val namet = DBInfo.UserTable.COL_FULLNAME
        val alamatt = DBInfo.UserTable.COL_ALAMAT
        val jkt = DBInfo.UserTable.COL_JENKAL
        var sql = "UPDATE "+ namatablet + " SET "+
                passt+"='"+passin+"', "+namet+"='"+fullnamein+"', "+alamatt+"='"+jenkalin+"', "+jkt+"='"+alamatin+"' "+
                "WHERE "+emailt+"='"+emailin+"'"
        db.execSQL(sql)
    }
    fun updateCake(idin: String, namein:String, pricein: String){
        val db = writableDatabase
        val namatablet = DBInfo.CakeTable.TABLE_NAME
        val idt = DBInfo.CakeTable.COL_ID
        val namet = DBInfo.CakeTable.COL_NAME
        val pricet = DBInfo.CakeTable.COL_PRICE
        var sql = "UPDATE "+ namatablet + " SET "+
                namet+"='"+namein+"', "+pricet+"='"+pricein+"' "+
                "WHERE "+idt+"='"+idin+"'"
        db.execSQL(sql)
    }
    fun updateIcecream(idin: String, namein:String, pricein: String){
        val db = writableDatabase
        val namatablet = DBInfo.IcecreamTable.TABLE_NAME
        val idt = DBInfo.IcecreamTable.COL_ID
        val namet = DBInfo.IcecreamTable.COL_NAME
        val pricet = DBInfo.IcecreamTable.COL_PRICE
        var sql = "UPDATE "+ namatablet + " SET "+
                namet+"='"+namein+"', "+pricet+"='"+pricein+"' "+
                "WHERE "+idt+"='"+idin+"'"
        db.execSQL(sql)
    }
}