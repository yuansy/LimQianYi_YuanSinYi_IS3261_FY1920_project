package com.example.limqianyi_yuansinyi_is3261_fy1920_project


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

/**
 * A simple [Fragment] subclass.
 */
class FragmentScan : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_fragment_scan, container, false)
        val view = inflater.inflate(R.layout.fragment_fragment_scan, container, false)

        val butScan = view.findViewById<Button>(R.id.btnScan)
        butScan.setOnClickListener{
            val intent = Intent (getActivity(), ActivityScan::class.java)
            getActivity()!!.startActivity(intent)
        }
        return view
    }


}
