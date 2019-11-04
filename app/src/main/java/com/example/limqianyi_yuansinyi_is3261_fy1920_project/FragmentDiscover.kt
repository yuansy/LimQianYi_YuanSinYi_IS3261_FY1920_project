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
class FragmentDiscover : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_fragment_discover, container, false)
        val view = inflater.inflate(R.layout.fragment_fragment_discover, container, false)

        val butDiscover = view.findViewById<Button>(R.id.btnDiscover)
        butDiscover.setOnClickListener{
            val intent = Intent (getActivity(), MainActivity::class.java)
            getActivity()!!.startActivity(intent)
        }
        return view

    }


}
