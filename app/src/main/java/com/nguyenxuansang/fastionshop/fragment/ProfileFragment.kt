package com.nguyenxuansang.fastionshop.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_profile, container, false)
        val btn_logout= view.findViewById<Button>(R.id.btn_logout)
        btn_logout.setOnClickListener {
            val intent = Intent(context,LoginActivity::class.java)
            startActivity(intent)
        }
        return view
    }


}
