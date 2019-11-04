package com.example.limqianyi_yuansinyi_is3261_fy1920_project

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class FoodDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Food.db"

        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FoodTableInfo.TABLE_NAME + "(" +
                    FoodTableInfo.COLUMN_ID + " TEXT PRIMARY KEY," +
                    FoodTableInfo.COLUMN_NAME + " TEXT," +
                    FoodTableInfo.COLUMN_PRICE + " TEXT," +
                    FoodTableInfo.COLUMN_PRICEDISCOUNT + " TEXT," +
                    FoodTableInfo.COLUMN_PRICEINCREASE + " TEXT," +
                    FoodTableInfo.COLUMN_CALORIE + " TEXT," +
                    FoodTableInfo.COLUMN_CATEGORY + " TEXT," +
                    FoodTableInfo.COLUMN_LOCATION + " TEXT," +
                    FoodTableInfo.COLUMN_INGREDIENT1 + " TEXT," +
                    FoodTableInfo.COLUMN_INGREDIENT2 + " TEXT," +
                    FoodTableInfo.COLUMN_INGREDIENT3 + " TEXT," +
                    FoodTableInfo.COLUMN_INGREDIENT4 + " TEXT," +
                    FoodTableInfo.COLUMN_CURRSTATUS + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +
                FoodTableInfo.TABLE_NAME

    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("TAGcreate", SQL_CREATE_ENTRIES)
        db?.execSQL(SQL_CREATE_ENTRIES)
        Log.d("TAGdone", "fooddone")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
        //onUpgrade(db, oldVersion, newVersion)
    }


    fun insertFood(food: FoodDataRecord): Boolean {
        val db = writableDatabase
        val values = ContentValues()

        values.put(FoodTableInfo.COLUMN_ID, food.ID)
        values.put(FoodTableInfo.COLUMN_NAME, food.name)
        values.put(FoodTableInfo.COLUMN_PRICE, food.price)
        values.put(FoodTableInfo.COLUMN_PRICEDISCOUNT, food.priceDiscount)
        values.put(FoodTableInfo.COLUMN_PRICEINCREASE, food.priceIncrease)
        values.put(FoodTableInfo.COLUMN_CALORIE, food.calorie)
        values.put(FoodTableInfo.COLUMN_CATEGORY, food.category)
        values.put(FoodTableInfo.COLUMN_LOCATION, food.location)
        values.put(FoodTableInfo.COLUMN_INGREDIENT1, food.ingredient1)
        values.put(FoodTableInfo.COLUMN_INGREDIENT2, food.ingredient2)
        values.put(FoodTableInfo.COLUMN_INGREDIENT3, food.ingredient3)
        values.put(FoodTableInfo.COLUMN_INGREDIENT4, food.ingredient4)
        values.put(FoodTableInfo.COLUMN_CURRSTATUS, food.currStatus)
        val newRowId = db.insert(FoodTableInfo.TABLE_NAME, null, values)
        return true
    }

    fun deleteFood(ID: String): Boolean {
        val db = writableDatabase
        val selection = FoodTableInfo.COLUMN_ID + " LIKE ? "
        val selectionArgs = arrayOf(ID)
        db.delete(FoodTableInfo.TABLE_NAME, selection, selectionArgs)

        return true
    }

    fun readFood(ID: String): ArrayList<FoodDataRecord> {
        val food = ArrayList<FoodDataRecord>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(
                "select * from " +
                        FoodTableInfo.TABLE_NAME + " WHERE " +
                        FoodTableInfo.COLUMN_ID + "='" + ID + "'", null
            )
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var name: String
        var price: Float
        var priceDiscount: Float
        var priceIncrease: Float
        var calorie: Int
        var category: String
        var location: String
        var ingredient1: String
        var ingredient2: String
        var ingredient3: String
        var ingredient4: String
        var currStatus: Int
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                name = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_NAME))
                price = cursor.getFloat(cursor.getColumnIndex(FoodTableInfo.COLUMN_PRICE))
                priceDiscount = cursor.getFloat(cursor.getColumnIndex(FoodTableInfo.COLUMN_PRICEDISCOUNT))
                priceIncrease = cursor.getFloat(cursor.getColumnIndex(FoodTableInfo.COLUMN_PRICEINCREASE))
                calorie = cursor.getInt(cursor.getColumnIndex(FoodTableInfo.COLUMN_CALORIE))
                category = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_CATEGORY))
                location = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_LOCATION))
                ingredient1 = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_INGREDIENT1))
                ingredient2 = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_INGREDIENT2))
                ingredient3 = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_INGREDIENT3))
                ingredient4 = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_INGREDIENT4))
                currStatus = cursor.getInt(cursor.getColumnIndex(FoodTableInfo.COLUMN_CURRSTATUS))
                food.add(FoodDataRecord(ID, name, price, priceDiscount, priceIncrease, calorie, category, location, ingredient1, ingredient2, ingredient3, ingredient4, currStatus))
                cursor.moveToNext()
            }
        }
        return food
    }

    fun readAllFood(): ArrayList<FoodDataRecord> {
        val food = ArrayList<FoodDataRecord>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + FoodTableInfo.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }
        var ID : String
        var name: String
        var price: Float
        var priceDiscount: Float
        var priceIncrease: Float
        var calorie: Int
        var category: String
        var location: String
        var ingredient1: String
        var ingredient2: String
        var ingredient3: String
        var ingredient4: String
        var currStatus: Int
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                ID = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_ID))
                name = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_NAME))
                price = cursor.getFloat(cursor.getColumnIndex(FoodTableInfo.COLUMN_PRICE))
                priceDiscount = cursor.getFloat(cursor.getColumnIndex(FoodTableInfo.COLUMN_PRICEDISCOUNT))
                priceIncrease = cursor.getFloat(cursor.getColumnIndex(FoodTableInfo.COLUMN_PRICEINCREASE))
                calorie = cursor.getInt(cursor.getColumnIndex(FoodTableInfo.COLUMN_CALORIE))
                category = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_CATEGORY))
                location = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_LOCATION))
                ingredient1 = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_INGREDIENT1))
                ingredient2 = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_INGREDIENT2))
                ingredient3 = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_INGREDIENT3))
                ingredient4 = cursor.getString(cursor.getColumnIndex(FoodTableInfo.COLUMN_INGREDIENT4))
                currStatus = cursor.getInt(cursor.getColumnIndex(FoodTableInfo.COLUMN_CURRSTATUS))
                food.add(FoodDataRecord(ID, name, price, priceDiscount, priceIncrease, calorie, category, location, ingredient1, ingredient2, ingredient3, ingredient4, currStatus))
                cursor.moveToNext()
            }
        }
        return food
    }

}
