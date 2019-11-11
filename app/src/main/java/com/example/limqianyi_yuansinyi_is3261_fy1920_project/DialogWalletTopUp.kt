package com.example.limqianyi_yuansinyi_is3261_fy1920_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_dialog_wallet_top_up.*
import java.text.SimpleDateFormat
import java.util.*

class DialogWalletTopUp : AppCompatActivity() {

    lateinit var transactionDBHelper: TransactionDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_wallet_top_up)

        transactionDBHelper = TransactionDBHelper(this)

        val butTopUp = findViewById<Button>(R.id.walletTopUpDone)
        butTopUp.setOnClickListener {
            val dateTime = SimpleDateFormat("dd MMM, HH:mm a").format(Date())
            val amount = walletTopUpAmount.text.toString().toFloat()
            if (amount>0){
                transactionDBHelper.insertTransaction(TransactionDataRecord(dateTime, "Top Up", amount, 0, "0"))
            }
            val myIntent = Intent(this, ActivityWallet::class.java)
            startActivity(myIntent)
        }

    }
}
