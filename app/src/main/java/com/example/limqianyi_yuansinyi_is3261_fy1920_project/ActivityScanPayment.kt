package com.example.limqianyi_yuansinyi_is3261_fy1920_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ActivityScanPayment : AppCompatActivity() {

    lateinit var foodDBHelper: FoodDBHelper
    lateinit var transactionDBHelper: TransactionDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_payment)

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


        val ID : String = intent.getStringExtra("ID")

        foodDBHelper = FoodDBHelper(this)
        val food = foodDBHelper.readFood(ID).get(0)
        val currStatus = food.currStatus

        transactionDBHelper = TransactionDBHelper(this)
        val allTransaction = transactionDBHelper.readAllTransaction()

        var balance = 0F
        allTransaction.forEach{
            balance += it.amount
        }


        val name = findViewById<TextView>(R.id.paymentName)
        name.text = food.name

        val calorie = findViewById<TextView>(R.id.paymentCalorie)
        calorie.text = food.calorie.toString() + "kcal"

        val foodImage = findViewById<ImageView>(R.id.paymentImage)
        val id = resources.getIdentifier(food.ID.toLowerCase(), "drawable", packageName)
        foodImage.setImageResource(id)

        val ingredient1 = findViewById<TextView>(R.id.paymentIngredient1)
        ingredient1.text = food.ingredient1
        val ingredient2 = findViewById<TextView>(R.id.paymentIngredient2)
        ingredient2.text = food.ingredient2
        val ingredient3 = findViewById<TextView>(R.id.paymentIngredient3)
        ingredient3.text = food.ingredient3
        val ingredient4 = findViewById<TextView>(R.id.paymentIngredient4)
        ingredient4.text = food.ingredient4

        val price = findViewById<TextView>(R.id.paymentPrice)
        price.text = "$" + "%.2f".format(food.price)

        val desc = findViewById<TextView>(R.id.paymentDesc)

        val balanceView = findViewById<TextView>(R.id.paymentBalance)
        balanceView.text = "$" + "%.2f".format(balance)

        val priceToPayView = findViewById<TextView>(R.id.paymentPriceToPay)

        var priceToPay = 0F

        if (currStatus==-1){
            desc.text = "Yay! The price has been reduced for you because you are lacking certain nutrition."
            priceToPay = food.priceDiscount
            priceToPayView.text = "$" + "%.2f".format(priceToPay)
            priceToPayView.setTextColor(getResources().getColor(R.color.greenPrice, null))
        }
        if (currStatus==0){
            priceToPay = food.price
            priceToPayView.text = "$" + "%.2f".format(priceToPay)
            desc.text = "Enjoy your meal!"
        }
        if (currStatus==1){
            desc.text = "Oh no! The price has been increased for you because you have been indulging in this just a little too much. Try healthier meals? :)"
            priceToPay = food.priceIncrease
            priceToPayView.text = "$" + "%.2f".format(priceToPay)
            priceToPayView.setTextColor(getResources().getColor(R.color.redPrice, null))
        }


        val btnPay = findViewById<Button>(R.id.btnPay)
        btnPay.setOnClickListener{

            if (balance < priceToPay) {
                Toast.makeText(this, "Please Top up!", Toast.LENGTH_LONG).show()
            } else {
                val dateTime = SimpleDateFormat("dd MMM, HH:mm a").format(Date())
                transactionDBHelper.insertTransaction(TransactionDataRecord(dateTime, food.name, -1*priceToPay, currStatus, ID))

                transactionDBHelper = TransactionDBHelper(this)
                updateFoodDB()

                val myIntent = Intent (this, ActivityScanPaid::class.java)
                myIntent.putExtra("paidStatement", "You have paid $" + "%.2f".format(priceToPay) + " for " + food.name)
                startActivity(myIntent)
            }
        }

    }

    fun updateFoodDB(){

        val allTransactionReversed = transactionDBHelper.readAllTransaction().asReversed()

        var categoryLast4 = ArrayList<String>()
        var IDLast4 = ArrayList<String>()
        allTransactionReversed.forEach{
            if (it.name != "Top Up" && categoryLast4.size != 4){
                IDLast4.add(it.ID)
                var food = foodDBHelper.readFood(it.ID).get(0)
                categoryLast4.add(food.category)
            }
        }

        val allFood = foodDBHelper.readAllFood()
        allFood.forEach{
            foodDBHelper.deleteFood(it.ID)
            foodDBHelper.insertFood(FoodDataRecord(it.ID, it.name, it.price, it.priceDiscount, it.priceIncrease, it.calorie,
                it.category, it.location, it.ingredient1, it.ingredient2, it.ingredient3, it.ingredient4, 0))
        }

        if (!categoryLast4.contains("vege")){
            allFood.forEach{
                if (it.category=="vege"){
                    foodDBHelper.deleteFood(it.ID)
                    foodDBHelper.insertFood(FoodDataRecord(it.ID, it.name, it.price, it.priceDiscount, it.priceIncrease, it.calorie,
                        it.category, it.location, it.ingredient1, it.ingredient2, it.ingredient3, it.ingredient4, -1))
                }
            }
        }
        if (!categoryLast4.contains("meat")){
            allFood.forEach{
                if (it.category=="meat"){
                    foodDBHelper.deleteFood(it.ID)
                    foodDBHelper.insertFood(FoodDataRecord(it.ID, it.name, it.price, it.priceDiscount, it.priceIncrease, it.calorie,
                        it.category, it.location, it.ingredient1, it.ingredient2, it.ingredient3, it.ingredient4, -1))
                }
            }
        }
        allFood.forEach{
            var count = 0
            var ID = it.ID
            IDLast4.forEach{
                if (ID==it){
                    count += 1
                }
            }
            if (it.category=="unhealthy" && count>=2){
                foodDBHelper.deleteFood(it.ID)
                foodDBHelper.insertFood(FoodDataRecord(it.ID, it.name, it.price, it.priceDiscount, it.priceIncrease, it.calorie,
                    it.category, it.location, it.ingredient1, it.ingredient2, it.ingredient3, it.ingredient4, 1))
            }
            if (count>=3){
                foodDBHelper.deleteFood(it.ID)
                foodDBHelper.insertFood(FoodDataRecord(it.ID, it.name, it.price, it.priceDiscount, it.priceIncrease, it.calorie,
                    it.category, it.location, it.ingredient1, it.ingredient2, it.ingredient3, it.ingredient4, 1))
            }
        }
    }
}
