package com.example.limqianyi_yuansinyi_is3261_fy1920_project

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_dialog_account.*

class DialogAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_account)

        val butAccountDone = findViewById<Button>(R.id.accountNameDone)
        butAccountDone.setOnClickListener{
            var accountName = accountNameChange.text.toString()

            val sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE)
            if (accountName!=""){
                val editor = sharedPreferences.edit()
                editor.putString("NAME", accountName)
                editor.apply()
            }
            val myIntent = Intent(this, ActivityAccount::class.java)
            startActivity(myIntent)
        }

    }
}
