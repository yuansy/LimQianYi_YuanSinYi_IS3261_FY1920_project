package com.example.limqianyi_yuansinyi_is3261_fy1920_project

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Build.ID
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.security.spec.RSAKeyGenParameterSpec.F4

class MainActivity : AppCompatActivity() {

    lateinit var foodDBHelper: FoodDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        val fragmentHistory = FragmentHistory()
        transaction.add(R.id.fragHistory, fragmentHistory)
        val fragmentScan = FragmentScan()
        transaction.add(R.id.fragScan, fragmentScan)
        val fragmentWallet = FragmentWallet()
        transaction.add(R.id.fragWallet, fragmentWallet)
        val fragmentAccount = FragmentAccount()
        transaction.add(R.id.fragAccount, fragmentAccount)

        val fragmentDiscoverDiscountForYou1 = FragmentDiscoverFood()
        transaction.add(R.id.discoverDiscountForYou1, fragmentDiscoverDiscountForYou1)
        val fragmentDiscoverDiscountForYou2 = FragmentDiscoverFood()
        transaction.add(R.id.discoverDiscountForYou2, fragmentDiscoverDiscountForYou2)
        val fragmentDiscoverDiscountForYou3 = FragmentDiscoverFood()
        transaction.add(R.id.discoverDiscountForYou3, fragmentDiscoverDiscountForYou3)

        val fragmentDiscoverPopularNow1 = FragmentDiscoverFood()
        transaction.add(R.id.discoverPopularNow1, fragmentDiscoverPopularNow1)
        val fragmentDiscoverPopularNow2 = FragmentDiscoverFood()
        transaction.add(R.id.discoverPopularNow2, fragmentDiscoverPopularNow2)
        val fragmentDiscoverPopularNow3 = FragmentDiscoverFood()
        transaction.add(R.id.discoverPopularNow3, fragmentDiscoverPopularNow3)

        transaction.commit()

        foodDBHelper = FoodDBHelper(this)
        initFoodDB() // only for first time

        var food = foodDBHelper.readAllFood()


        val foodDiscountForYou = ArrayList<FoodDataRecord>()
        food.forEach{
            if (it.currStatus == -1 && foodDiscountForYou.size != 3) {
                foodDiscountForYou.add(it)
                foodDBHelper.deleteFood(it.ID)
                foodDBHelper.insertFood(FoodDataRecord(it.ID, it.name, it.price, it.priceDiscount, it.priceIncrease, it.calorie,
                    it.category, it.location, it.ingredient1, it.ingredient2, it.ingredient3, it.ingredient4, -1))
            }
        }
        //extra discount
        food.forEach{
            if (it.currStatus == 0 && it.category != "unhealthy" && foodDiscountForYou.size != 3) {
                foodDiscountForYou.add(it)
                foodDBHelper.deleteFood(it.ID)
                foodDBHelper.insertFood(FoodDataRecord(it.ID, it.name, it.price, it.priceDiscount, it.priceIncrease, it.calorie,
                    it.category, it.location, it.ingredient1, it.ingredient2, it.ingredient3, it.ingredient4, -1))
            }
        }

        var foodDiscount1 = foodDiscountForYou.get(0)
        val mArgs1 = Bundle()
        mArgs1.putString("ID", foodDiscount1.ID)
        mArgs1.putString("LINE1", foodDiscount1.name)
        mArgs1.putString("LINE2", "$" + "%.2f".format(foodDiscount1.priceDiscount))
        mArgs1.putString("LINE3", foodDiscount1.calorie.toString() + " kcal")
        fragmentDiscoverDiscountForYou1.setArguments(mArgs1)

        var foodDiscount2 = foodDiscountForYou.get(1)
        val mArgs2 = Bundle()
        mArgs2.putString("ID", foodDiscount2.ID)
        mArgs2.putString("LINE1", foodDiscount2.name)
        mArgs2.putString("LINE2", "$" + "%.2f".format(foodDiscount2.priceDiscount))
        mArgs2.putString("LINE3", foodDiscount2.calorie.toString() + " kcal")
        fragmentDiscoverDiscountForYou2.setArguments(mArgs2)

        var foodDiscount3 = foodDiscountForYou.get(2)
        val mArgs3 = Bundle()
        mArgs3.putString("ID", foodDiscount3.ID)
        mArgs3.putString("LINE1", foodDiscount3.name)
        mArgs3.putString("LINE2", "$" + "%.2f".format(foodDiscount3.priceDiscount))
        mArgs3.putString("LINE3", foodDiscount3.calorie.toString() + " kcal")
        fragmentDiscoverDiscountForYou3.setArguments(mArgs3)


        val foodPopularNow = ArrayList<FoodDataRecord>()
        food.forEach{
            if (it.currStatus != -1 && foodPopularNow.size != 3 && !foodDiscountForYou.contains(it) && it.category != "unhealthy") {
                foodPopularNow.add(it)
            }
        }

        var foodPopular1 = foodPopularNow.get(0)
        val mArgs4 = Bundle()
        mArgs4.putString("ID", foodPopular1.ID)
        mArgs4.putString("LINE1", foodPopular1.name)
        mArgs4.putString("LINE2", "$" + "%.2f".format(foodPopular1.priceDiscount))
        mArgs4.putString("LINE3", foodPopular1.calorie.toString() + " kcal")
        fragmentDiscoverPopularNow1.setArguments(mArgs4)

        var foodPopular2 = foodPopularNow.get(1)
        val mArgs5 = Bundle()
        mArgs5.putString("ID", foodPopular2.ID)
        mArgs5.putString("LINE1", foodPopular2.name)
        mArgs5.putString("LINE2", "$" + "%.2f".format(foodPopular2.priceDiscount))
        mArgs5.putString("LINE3", foodPopular2.calorie.toString() + " kcal")
        fragmentDiscoverPopularNow2.setArguments(mArgs5)

        var foodPopular3 = foodPopularNow.get(2)
        val mArgs6 = Bundle()
        mArgs6.putString("ID", foodPopular3.ID)
        mArgs6.putString("LINE1", foodPopular3.name)
        mArgs6.putString("LINE2", "$" + "%.2f".format(foodPopular3.priceDiscount))
        mArgs6.putString("LINE3", foodPopular3.calorie.toString() + " kcal")
        fragmentDiscoverPopularNow3.setArguments(mArgs6)



        val butSearch = findViewById<Button>(R.id.btnSearch)
        butSearch.setOnClickListener {
            val intent = Intent(this, ActivityViewMore::class.java)
            var foodNameSearch = discoverSearch.text.toString()
            var searchID = ""
            food.forEach{
                if (foodNameSearch.toLowerCase()==it.name.toLowerCase()){
                    searchID = it.ID
                    intent.putExtra("ID", searchID)
                    intent.putExtra("type", "search")
                    startActivity(intent)
                }
            }
            if (searchID==""){
                Toast.makeText(this, "Oops! No such food!", Toast.LENGTH_LONG).show()
            }
        }
    }


    fun addFood(food: FoodDataRecord) {
        foodDBHelper.insertFood(food)
    }

    fun initFoodDB() {
        addFood(FoodDataRecord("F1", "Ban Mian", 3F, 2.8F, 3.2F, 300,
            "mix", "Utown Foodclique", "vegetables", "egg noodles",
            "pork", "egg", 0 ))
        addFood(FoodDataRecord("F2", "Salad", 4F, 3.7F, 4.3F, 152,
            "vege", "Computing Terrace", "vegetables", "tomato",
            "peanut", "sesame oil", 0))
        addFood(FoodDataRecord("F3", "Cai Fan", 3F, 2.8F, 3.2F, 350,
            "mix", "Arts Deck", "vegetables", "meat",
            "egg", "white rice", 0))
        addFood(FoodDataRecord("F4", "Acai Bowl", 6.5F, 6F, 7F, 300,
            "vege", "Engineering Techno", "oat meal", "banana",
            "kiwi", "peanut butter", 0))
        addFood(FoodDataRecord("F5", "Ginseng Chicken", 4.5F, 4.2F, 4.8F, 197,
            "meat", "Computing Terrace", "chicken", "kimchi",
            "anchovies", "white rice", 0))
        addFood(FoodDataRecord("F6", "Yong Tau Foo", 4F, 3.7F, 4.3F, 297,
            "mix", "Science Frontier", "fish ball", "dumplings",
            "seaweed", "tau pok", 0))
        addFood(FoodDataRecord("F7", "Chicken Rice", 3.5F, 3.3F, 3.7F, 500,
            "meat", "Engineering Techno", "chicken", "white rice",
            "cucumber", "sesame oil, salt", 0))
        addFood(FoodDataRecord("F8", "Duck Rice", 4F, 3.7F, 4.3F, 680,
            "meat", "Arts Deck", "duck", "white rice",
            "cucumber", "gravy and oil", 0))
        addFood(FoodDataRecord("F9", "Beef Bulgogi", 5.5F, 5F, 6F, 339,
            "meat", "Utown Foodclique", "beef", "kimchi",
            "white rice", "soy sauce", 0))
        addFood(FoodDataRecord("F10", "Xiao Guo Mian", 5F, 4.6F, 5.4F, 350,
            "mix", "Computing Terrace", "vegetables", "egg noodles",
            "pork", "chicken broth", 0))
        addFood(FoodDataRecord("F11", "Nasi Lemak", 4.5F, 4.5F, 4.8F, 579,
        "unhealthy", "Engineering Techno", "fried chicken", "anchovies",
        "sambal", "egg", 0))
        addFood(FoodDataRecord("F12", "Mala Hotpot", 6F, 6F, 6.5F, 700,
            "unhealthy", "Utown Foodclique", "chilli pepper sauce", "pork",
            "lotus root", "black fungus", 0))
        addFood(FoodDataRecord("F13", "Fish and Chips", 5F, 5F, 5.4F, 580,
            "unhealthy", "Arts Deck", "fish fillet", "chips",
            "egg", "oil", 0))
        addFood(FoodDataRecord("F14", "Vegetarian", 3F, 2.8F, 3.2F, 290,
            "vege", "Science Frontier", "brocolli", "tomato",
            "egg", "white rice", 0))
        addFood(FoodDataRecord("F15", "Fish Soup", 4F, 3.7F, 4.3F, 210,
            "meat", "Science Frontier", "fish", "vegetables",
            "tomato", "broth", 0))
    }
}
