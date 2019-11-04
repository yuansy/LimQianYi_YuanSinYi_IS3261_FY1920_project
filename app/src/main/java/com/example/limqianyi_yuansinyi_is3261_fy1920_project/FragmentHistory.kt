package com.example.limqianyi_yuansinyi_is3261_fy1920_project


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

/**
 * A simple [Fragment] subclass.
 */
class FragmentHistory : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_fragment_history, container, false)
        val view = inflater.inflate(R.layout.fragment_fragment_history, container, false)

        val butHistory = view.findViewById<Button>(R.id.btnHistory)
        butHistory.setOnClickListener{
            val intent = Intent (getActivity(), ActivityHistory::class.java)
            getActivity()!!.startActivity(intent)
        }
        return view

    }


}
