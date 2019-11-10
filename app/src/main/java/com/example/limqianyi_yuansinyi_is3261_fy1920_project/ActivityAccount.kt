package com.example.limqianyi_yuansinyi_is3261_fy1920_project

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_account.*

class ActivityAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

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

        transaction.commit()

        val hellotv = findViewById<TextView>(R.id.helloMessage)
        val butAccountName = findViewById<ImageView>(R.id.accountName)
        butAccountName.setOnClickListener{
            val myIntent = Intent(this, DialogAccount::class.java)
            startActivity(myIntent)
        }

        val sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        if (sharedPreferences.getString("NAME", "") != "") {
            val accountName = sharedPreferences.getString("NAME", "Your Name")
            hellotv.setText("Hello, " + accountName + "!")
        } else {
            hellotv.setText("Hello!")
        }

        val butCalorie = findViewById<ImageView>(R.id.btnCalorie)
        butCalorie.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_VIEW)
            myIntent.data = Uri.parse("https://www.healthhub.sg/programmes/94/calorie-calculator")
            startActivity(myIntent)
        }

        val butBMI = findViewById<ImageView>(R.id.btnBMI)
        butBMI.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_VIEW)
            myIntent.data = Uri.parse("https://www.healthhub.sg/programmes/93/bmi-calculator")
            startActivity(myIntent)
        }

    }

}
