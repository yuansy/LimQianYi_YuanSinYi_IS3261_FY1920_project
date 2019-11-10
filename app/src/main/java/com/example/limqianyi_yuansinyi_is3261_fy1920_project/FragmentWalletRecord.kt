package com.example.limqianyi_yuansinyi_is3261_fy1920_project


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.R.id
import android.graphics.Typeface
import android.text.Html


/**
 * A simple [Fragment] subclass.
 */
class FragmentWalletRecord : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment_wallet_record, container, false)

        var args = getArguments()

        if (args != null){
            var Line1 = args.getString("LINE1")
            var twLine1 = view.findViewById<TextView>(R.id.walletRecordName)
            twLine1.setText(Line1)
            twLine1.setTypeface(null, Typeface.BOLD)

            var Line2 = args.getString("LINE2")
            var twLine2 = view.findViewById<TextView>(R.id.walletRecordDatetime)
            twLine2.setText(Line2)

            var Line3 = args.getString("LINE3")
            var twLine3 = view.findViewById<TextView>(R.id.walletRecordAmount)
            twLine3.setText(Line3)

            if (Line1=="Top Up"){
                twLine3.setTextColor(getResources().getColor(R.color.greenPrice, null))
            }
        }

        return view
    }


}
