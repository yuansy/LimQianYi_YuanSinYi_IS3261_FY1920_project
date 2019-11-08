package com.example.limqianyi_yuansinyi_is3261_fy1920_project

import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class ActivityWallet : AppCompatActivity() {

    lateinit var transactionDBHelper: TransactionDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        transactionDBHelper = TransactionDBHelper(this)
        val allTransaction = transactionDBHelper.readAllTransaction()


        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        val fragmentDiscover = FragmentDiscover()
        transaction.add(R.id.fragDiscover, fragmentDiscover)
        val fragmentHistory = FragmentHistory()
        transaction.add(R.id.fragHistory, fragmentHistory)
        val fragmentScan = FragmentScan()
        transaction.add(R.id.fragScan, fragmentScan)
        val fragmentAccount = FragmentAccount()
        transaction.add(R.id.fragAccount, fragmentAccount)

        val fragmentRecord1 = FragmentWalletRecord()
        transaction.add(R.id.wallet1, fragmentRecord1)
        val fragmentRecord2 = FragmentWalletRecord()
        transaction.add(R.id.wallet2, fragmentRecord2)
        val fragmentRecord3 = FragmentWalletRecord()
        transaction.add(R.id.wallet3, fragmentRecord3)
        val fragmentRecord4 = FragmentWalletRecord()
        transaction.add(R.id.wallet4, fragmentRecord4)
        val fragmentRecord5 = FragmentWalletRecord()
        transaction.add(R.id.wallet5, fragmentRecord5)
        val fragmentRecord6 = FragmentWalletRecord()
        transaction.add(R.id.wallet6, fragmentRecord6)
        val fragmentRecord7 = FragmentWalletRecord()
        transaction.add(R.id.wallet7, fragmentRecord7)
        val fragmentRecord8 = FragmentWalletRecord()
        transaction.add(R.id.wallet8, fragmentRecord8)
        val fragmentRecord9 = FragmentWalletRecord()
        transaction.add(R.id.wallet9, fragmentRecord9)
        val fragmentRecord10 = FragmentWalletRecord()
        transaction.add(R.id.wallet10, fragmentRecord10)

        transaction.commit()

        val butTopUp = findViewById<Button>(R.id.walletTopUp)
        butTopUp.setOnClickListener{
            val myIntent = Intent(this, DialogWalletTopUp::class.java)
            startActivity(myIntent)
        }

        var balance = 0F
        allTransaction.forEach{
            balance += it.amount
        }

        val balanceView = findViewById<TextView>(R.id.walletBalance)
        balanceView.text = "$" + "%.2f".format(balance)


        var transactionDisplay : TransactionDataRecord
        var mArgs = Bundle()
        if (allTransaction.size>=1) {
            transactionDisplay = allTransaction.get(allTransaction.size-1)
            mArgs = Bundle()
            mArgs.putString("LINE1", transactionDisplay.name)
            mArgs.putString("LINE2", transactionDisplay.dateTime)
            mArgs.putString("LINE3", amountStirng(transactionDisplay.amount))
            fragmentRecord1.setArguments(mArgs)
        }
        if (allTransaction.size>=2) {
            transactionDisplay = allTransaction.get(allTransaction.size-2)
            mArgs = Bundle()
            mArgs.putString("LINE1", transactionDisplay.name)
            mArgs.putString("LINE2", transactionDisplay.dateTime)
            mArgs.putString("LINE3", amountStirng(transactionDisplay.amount))
            fragmentRecord2.setArguments(mArgs)
        }
        if (allTransaction.size>=3) {
            transactionDisplay = allTransaction.get(allTransaction.size-3)
            mArgs = Bundle()
            mArgs.putString("LINE1", transactionDisplay.name)
            mArgs.putString("LINE2", transactionDisplay.dateTime)
            mArgs.putString("LINE3", amountStirng(transactionDisplay.amount))
            fragmentRecord3.setArguments(mArgs)
        }
        if (allTransaction.size>=4) {
            transactionDisplay = allTransaction.get(allTransaction.size-4)
            mArgs = Bundle()
            mArgs.putString("LINE1", transactionDisplay.name)
            mArgs.putString("LINE2", transactionDisplay.dateTime)
            mArgs.putString("LINE3", amountStirng(transactionDisplay.amount))
            fragmentRecord4.setArguments(mArgs)
        }
        if (allTransaction.size>=5) {
            transactionDisplay = allTransaction.get(allTransaction.size-5)
            mArgs = Bundle()
            mArgs.putString("LINE1", transactionDisplay.name)
            mArgs.putString("LINE2", transactionDisplay.dateTime)
            mArgs.putString("LINE3", amountStirng(transactionDisplay.amount))
            fragmentRecord5.setArguments(mArgs)
        }
        if (allTransaction.size>=6) {
            transactionDisplay = allTransaction.get(allTransaction.size-6)
            mArgs = Bundle()
            mArgs.putString("LINE1", transactionDisplay.name)
            mArgs.putString("LINE2", transactionDisplay.dateTime)
            mArgs.putString("LINE3", amountStirng(transactionDisplay.amount))
            fragmentRecord6.setArguments(mArgs)
        }
        if (allTransaction.size>=7) {
            transactionDisplay = allTransaction.get(allTransaction.size-7)
            mArgs = Bundle()
            mArgs.putString("LINE1", transactionDisplay.name)
            mArgs.putString("LINE2", transactionDisplay.dateTime)
            mArgs.putString("LINE3", amountStirng(transactionDisplay.amount))
            fragmentRecord7.setArguments(mArgs)
        }
        if (allTransaction.size>=8) {
            transactionDisplay = allTransaction.get(allTransaction.size-8)
            mArgs = Bundle()
            mArgs.putString("LINE1", transactionDisplay.name)
            mArgs.putString("LINE2", transactionDisplay.dateTime)
            mArgs.putString("LINE3", amountStirng(transactionDisplay.amount))
            fragmentRecord8.setArguments(mArgs)
        }
        if (allTransaction.size>=9) {
            transactionDisplay = allTransaction.get(allTransaction.size-9)
            mArgs = Bundle()
            mArgs.putString("LINE1", transactionDisplay.name)
            mArgs.putString("LINE2", transactionDisplay.dateTime)
            mArgs.putString("LINE3", amountStirng(transactionDisplay.amount))
            fragmentRecord9.setArguments(mArgs)
        }
        if (allTransaction.size>=10) {
            transactionDisplay = allTransaction.get(allTransaction.size-10)
            mArgs = Bundle()
            mArgs.putString("LINE1", transactionDisplay.name)
            mArgs.putString("LINE2", transactionDisplay.dateTime)
            mArgs.putString("LINE3", amountStirng(transactionDisplay.amount))
            fragmentRecord10.setArguments(mArgs)
        }

    }

    fun amountStirng(amount: Float): String {
        if (amount<0){
            return "-$" + "%.2f".format(-1*amount)
        } else {
            return "$" + "%.2f".format(amount)
        }
    }
}
