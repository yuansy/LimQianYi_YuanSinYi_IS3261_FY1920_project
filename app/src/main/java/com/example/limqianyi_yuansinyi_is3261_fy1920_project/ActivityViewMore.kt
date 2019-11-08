package com.example.limqianyi_yuansinyi_is3261_fy1920_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_view_more.*

class ActivityViewMore : AppCompatActivity() {

    lateinit var foodDBHelper: FoodDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_more)

        foodDBHelper = FoodDBHelper(this)

        var ID : String = intent.getStringExtra("ID")
        var type : String = intent.getStringExtra("type")

        var food = foodDBHelper.readFood(ID).get(0)

        viewMoreName.text = food.name
        viewMoreIngredient1.text = food.ingredient1
        viewMoreIngredient2.text = food.ingredient2
        viewMoreIngredient3.text = food.ingredient3
        viewMoreIngredient4.text = food.ingredient4
        viewMoreLocation.text = food.location.toUpperCase()

        if (type=="search"){
            viewMorePrice.text = food.price.toString()
            var priceToPay = findViewById<TextView>(R.id.viewMorePriceToPay)
            var status = food.currStatus
            if (status==-1){
                priceToPay.text = food.priceDiscount.toString()
                priceToPay.setTextColor(getResources().getColor(R.color.greenPrice, null))
            } else if (status==1){
                priceToPay.text = food.priceIncrease.toString()
                priceToPay.setTextColor(getResources().getColor(R.color.redPrice, null))
            }
        }
    }
}
