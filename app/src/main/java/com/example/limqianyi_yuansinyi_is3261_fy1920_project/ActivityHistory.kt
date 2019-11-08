package com.example.limqianyi_yuansinyi_is3261_fy1920_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ActivityHistory : AppCompatActivity() {

    lateinit var foodDBHelper: FoodDBHelper
    lateinit var transactionDBHelper: TransactionDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        val fragmentDiscover = FragmentDiscover()
        transaction.add(R.id.fragDiscover, fragmentDiscover)
        val fragmentScan = FragmentScan()
        transaction.add(R.id.fragScan, fragmentScan)
        val fragmentWallet = FragmentWallet()
        transaction.add(R.id.fragWallet, fragmentWallet)
        val fragmentAccount = FragmentAccount()
        transaction.add(R.id.fragAccount, fragmentAccount)

        val fragmentRecord1 = FragmentHistoryRecord()
        transaction.add(R.id.history1, fragmentRecord1)
        val fragmentRecord2 = FragmentHistoryRecord()
        transaction.add(R.id.history2, fragmentRecord2)
        val fragmentRecord3 = FragmentHistoryRecord()
        transaction.add(R.id.history3, fragmentRecord3)
        val fragmentRecord4 = FragmentHistoryRecord()
        transaction.add(R.id.history4, fragmentRecord4)

        transaction.commit()

        foodDBHelper = FoodDBHelper(this)
        transactionDBHelper = TransactionDBHelper(this)

        val allTransactionReversed = transactionDBHelper.readAllTransaction().asReversed()

        var historyLast4 = ArrayList<TransactionDataRecord>()
        allTransactionReversed.forEach{
            if (it.name != "Top Up" && historyLast4.size != 4){
                historyLast4.add(it)
            }
        }

        var historyDisplay : TransactionDataRecord
        var status : Int
        var food : FoodDataRecord
        var mArgs = Bundle()

        if (historyLast4.size>=1) {
            historyDisplay = historyLast4.get(0)
            food = foodDBHelper.readFood(historyDisplay.ID).get(0)
            status = historyDisplay.status
            mArgs = Bundle()
            mArgs.putString("LINE1", food.name)
            mArgs.putString("LINE2", "$" + "%.2f".format(food.price))
            if (status==-1){
                mArgs.putString("LINE3", "$" + "%.2f".format(food.priceDiscount))
            } else if (status==0) {
                mArgs.putString("LINE3", "")
            } else if (status==1) {
                mArgs.putString("LINE3", "$" + "%.2f".format(food.priceIncrease))
            }
            mArgs.putString("LINE4", food.calorie.toString() + " kcal")
            mArgs.putString("LINE5", historyDisplay.dateTime.take(6))
            mArgs.putString("STATUS", status.toString())
            mArgs.putString("ID", food.ID)
            fragmentRecord1.setArguments(mArgs)
        }
        if (historyLast4.size>=2) {
            historyDisplay = historyLast4.get(1)
            food = foodDBHelper.readFood(historyDisplay.ID).get(0)
            status = historyDisplay.status
            mArgs = Bundle()
            mArgs.putString("LINE1", food.name)
            mArgs.putString("LINE2", "$" + "%.2f".format(food.price))
            if (status==-1){
                mArgs.putString("LINE3", "$" + "%.2f".format(food.priceDiscount))
            } else if (status==0) {
                mArgs.putString("LINE3", "")
            } else if (status==1) {
                mArgs.putString("LINE3", "$" + "%.2f".format(food.priceIncrease))
            }
            mArgs.putString("LINE4", food.calorie.toString() + " kcal")
            mArgs.putString("LINE5", historyDisplay.dateTime.take(6))
            mArgs.putString("STATUS", status.toString())
            mArgs.putString("ID", food.ID)
            fragmentRecord2.setArguments(mArgs)
        }
        if (historyLast4.size>=3) {
            historyDisplay = historyLast4.get(2)
            food = foodDBHelper.readFood(historyDisplay.ID).get(0)
            status = historyDisplay.status
            mArgs = Bundle()
            mArgs.putString("LINE1", food.name)
            mArgs.putString("LINE2", "$" + "%.2f".format(food.price))
            if (status==-1){
                mArgs.putString("LINE3", "$" + "%.2f".format(food.priceDiscount))
            } else if (status==0) {
                mArgs.putString("LINE3", "")
            } else if (status==1) {
                mArgs.putString("LINE3", "$" + "%.2f".format(food.priceIncrease))
            }
            mArgs.putString("LINE4", food.calorie.toString() + " kcal")
            mArgs.putString("LINE5", historyDisplay.dateTime.take(6))
            mArgs.putString("STATUS", status.toString())
            mArgs.putString("ID", food.ID)
            fragmentRecord3.setArguments(mArgs)
        }
        if (historyLast4.size>=4) {
            historyDisplay = historyLast4.get(3)
            food = foodDBHelper.readFood(historyDisplay.ID).get(0)
            status = historyDisplay.status
            mArgs = Bundle()
            mArgs.putString("LINE1", food.name)
            mArgs.putString("LINE2", "$" + "%.2f".format(food.price))
            if (status==-1){
                mArgs.putString("LINE3", "$" + "%.2f".format(food.priceDiscount))
            } else if (status==0) {
                mArgs.putString("LINE3", "")
            } else if (status==1) {
                mArgs.putString("LINE3", "$" + "%.2f".format(food.priceIncrease))
            }
            mArgs.putString("LINE4", food.calorie.toString() + " kcal")
            mArgs.putString("LINE5", historyDisplay.dateTime.take(6))
            mArgs.putString("STATUS", status.toString())
            mArgs.putString("ID", food.ID)
            fragmentRecord4.setArguments(mArgs)
        }

    }
}
