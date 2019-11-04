package com.example.limqianyi_yuansinyi_is3261_fy1920_project

import android.provider.BaseColumns

class FoodTableInfo : BaseColumns {

    companion object {
        val TABLE_NAME = "food"
        val COLUMN_ID = "ID"
        val COLUMN_NAME = "name"
        val COLUMN_PRICE = "price"
        val COLUMN_PRICEDISCOUNT = "priceDiscount"
        val COLUMN_PRICEINCREASE = "priceIncrease"
        val COLUMN_CALORIE = "calorie"
        val COLUMN_CATEGORY = "category"
        val COLUMN_LOCATION = "location"
        val COLUMN_INGREDIENT1 = "ingredient1"
        val COLUMN_INGREDIENT2 = "ingredient2"
        val COLUMN_INGREDIENT3 = "ingredient3"
        val COLUMN_INGREDIENT4 = "ingredient4"
        val COLUMN_CURRSTATUS = "currStatus"
    }
}
