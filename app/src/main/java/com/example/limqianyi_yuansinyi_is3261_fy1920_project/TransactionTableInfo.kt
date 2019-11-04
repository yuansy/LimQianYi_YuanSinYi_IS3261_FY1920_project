package com.example.limqianyi_yuansinyi_is3261_fy1920_project

import android.provider.BaseColumns

class TransactionTableInfo : BaseColumns {

    companion object {
        val TABLE_NAME = "transaction123"
        val COLUMN_DATETIME = "dateTime"
        val COLUMN_NAME = "name"
        val COLUMN_AMOUNT = "amount"
        val COLUMN_STATUS = "status"
        val COLUMN_ID = "ID"
    }
}