package com.ahmadraihan.uas201804010
import android.provider.BaseColumns
object DBInfo {
    class UserTable: BaseColumns {
        companion object {
            val TABLE_NAME = "user"
            val COL_EMAIL = "email"
            val COL_PASS = "pass"
            val COL_FULLNAME = "fullname"
            val COL_ALAMAT = "alamat"
            val COL_JENKAL = "jeniskelamin"
            val COL_JUMLAH = "jumlah"
        }
    }

    class CakeTable: BaseColumns {
        companion object {
            val TABLE_NAME = "cake"
            val COL_ID = "id"
            val COL_NAME = "name"
            val COL_PRICE = "price"
        }
    }

    class IcecreamTable: BaseColumns {
        companion object {
            val TABLE_NAME = "icecream"
            val COL_ID = "id"
            val COL_NAME = "name"
            val COL_PRICE = "price"
        }
    }
}