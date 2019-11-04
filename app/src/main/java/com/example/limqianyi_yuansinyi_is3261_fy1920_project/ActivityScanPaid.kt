package com.example.limqianyi_yuansinyi_is3261_fy1920_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class ActivityScanPaid : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_paid)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        val fragmentDiscover = FragmentDiscover()
        transaction.add(R.id.fragDiscover, fragmentDiscover)
        val fragmentHistory = FragmentHistory()
        transaction.add(R.id.fragHistory, fragmentHistory)
        val fragmentScan = FragmentScan()
        transaction.add(R.id.fragScan, fragmentScan)
        val fragmentWallet = FragmentWallet()
        transaction.add(R.id.fragWallet, fragmentWallet)
        val fragmentAccount = FragmentAccount()
        transaction.add(R.id.fragAccount, fragmentAccount)

        transaction.commit()

        val paidStatement : String = intent.getStringExtra("paidStatement")

        val paidStatementView = findViewById<TextView>(R.id.paidStatement)
        paidStatementView.text = paidStatement

        Toast.makeText(this, "Payment Success!", Toast.LENGTH_LONG).show()
    }
}
