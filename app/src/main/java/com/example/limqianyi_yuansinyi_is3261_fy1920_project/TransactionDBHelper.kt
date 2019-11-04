package com.example.limqianyi_yuansinyi_is3261_fy1920_project

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class TransactionDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Transaction.db"

        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TransactionTableInfo.TABLE_NAME + "(" +
                    TransactionTableInfo.COLUMN_DATETIME + " TEXT," +
                    TransactionTableInfo.COLUMN_NAME + " TEXT," +
                    TransactionTableInfo.COLUMN_AMOUNT + " TEXT," +
                    TransactionTableInfo.COLUMN_STATUS + " TEXT," +
                    TransactionTableInfo.COLUMN_ID + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +
                TransactionTableInfo.TABLE_NAME

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
        //onUpgrade(db, oldVersion, newVersion)
    }

    fun insertTransaction(transaction: TransactionDataRecord): Boolean {
        val db = writableDatabase
        val values = ContentValues()

        values.put(TransactionTableInfo.COLUMN_DATETIME, transaction.dateTime)
        values.put(TransactionTableInfo.COLUMN_NAME, transaction.name)
        values.put(TransactionTableInfo.COLUMN_AMOUNT, transaction.amount)
        values.put(TransactionTableInfo.COLUMN_STATUS, transaction.status)
        values.put(TransactionTableInfo.COLUMN_ID, transaction.ID)
        val newRowId = db.insert(TransactionTableInfo.TABLE_NAME, null, values)

        return true
    }

    fun readAllTransaction(): ArrayList<TransactionDataRecord> {
        val transaction = ArrayList<TransactionDataRecord>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + TransactionTableInfo.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var dateTime : String
        var name : String
        var amount: Float
        var status: Int
        var ID: String
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                dateTime = cursor.getString(cursor.getColumnIndex(TransactionTableInfo.COLUMN_DATETIME))
                name = cursor.getString(cursor.getColumnIndex(TransactionTableInfo.COLUMN_NAME))
                amount = cursor.getFloat(cursor.getColumnIndex(TransactionTableInfo.COLUMN_AMOUNT))
                status = cursor.getInt(cursor.getColumnIndex(TransactionTableInfo.COLUMN_STATUS))
                ID = cursor.getString(cursor.getColumnIndex(TransactionTableInfo.COLUMN_ID))
                transaction.add(TransactionDataRecord(dateTime, name, amount, status, ID))
                cursor.moveToNext()
            }
        }
        return transaction
    }

}
