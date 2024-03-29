package com.example.limqianyi_yuansinyi_is3261_fy1920_project


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 */
class FragmentDiscoverFood : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment_discover_food, container, false)

        var args = getArguments()

        val ID = args!!.getString("ID")

        var id = resources.getIdentifier(ID.toLowerCase(), "drawable",
            activity?.packageName
        )
        var imgFood = view.findViewById<ImageView>(R.id.discoverFoodImage)
        imgFood.setImageResource(id)

        imgFood.setOnClickListener{
            val intent = Intent (getActivity(), ActivityViewMore::class.java)
            intent.putExtra("ID", ID)
            intent.putExtra("type", "search")
            getActivity()!!.startActivity(intent)
        }

        var Line1 = args!!.getString("LINE1")
        var twLine1 = view.findViewById<TextView>(R.id.discoverFoodLine1)
        twLine1.setText(Line1)

        var Line2 = args!!.getString("LINE2")
        var twLine2 = view.findViewById<TextView>(R.id.discoverFoodLine2)
        twLine2.setText(Line2)

        var Line3 = args!!.getString("LINE3")
        var twLine3 = view.findViewById<TextView>(R.id.discoverFoodLine3)
        twLine3.setText(Line3)

        return view
    }


}
