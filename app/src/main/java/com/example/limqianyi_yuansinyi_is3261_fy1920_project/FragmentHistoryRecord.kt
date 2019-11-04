package com.example.limqianyi_yuansinyi_is3261_fy1920_project


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 */
class FragmentHistoryRecord : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment_history_record, container, false)

        var args = getArguments()

        if (args != null){

            var imgFood = view.findViewById<ImageView>(R.id.historyFoodImage)
            imgFood.setImageResource(R.drawable.img_food)
            var imgCash = view.findViewById<ImageView>(R.id.historyCash)
            imgCash.setImageResource(R.drawable.img_cash)

            var Line1 = args.getString("LINE1")
            var twLine1 = view.findViewById<TextView>(R.id.historyFoodName)
            twLine1.setText(Line1)

            var Line2 = args.getString("LINE2")
            var twLine2 = view.findViewById<TextView>(R.id.historyFoodPrice)
            twLine2.setText(Line2)

            var Line3 = args.getString("LINE3")
            var twLine3 = view.findViewById<TextView>(R.id.historyFoodPriceActual)
            twLine3.setText(Line3)

            var Line4 = args.getString("LINE4")
            var twLine4 = view.findViewById<TextView>(R.id.historyFoodCalorie)
            twLine4.setText(Line4)

            var Line5 = args.getString("LINE5")
            var twLine5 = view.findViewById<TextView>(R.id.historyFoodDate)
            twLine5.setText(Line5)

            var status = args.getString("STATUS")
            if (status=="-1"){
                twLine3.setTextColor(getResources().getColor(R.color.greenPrice, null))
            } else if (status=="1"){
                twLine3.setTextColor(getResources().getColor(R.color.redPrice, null))
            }
        }

        return view
    }


}
