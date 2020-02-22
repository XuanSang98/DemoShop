package com.nguyenxuansang.fastionshop.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.adapter.TabFemaleAdapter
import kotlinx.android.synthetic.main.fragment_female.*

/**
 * A simple [Fragment] subclass.
 */
class MemaleFragment : Fragment() {
    var fragmentTransaction: FragmentTransaction? = null
    var fragment: Fragment? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_female, container, false)
        var tablayout = view.findViewById<TabLayout>(R.id.tablayout)
        var viewpager = view.findViewById<ViewPager>(R.id.viewpager)
        val adapter =
            context?.let { TabFemaleAdapter(it, activity!!.supportFragmentManager, tablayout!!.tabCount) }
        viewpager!!.adapter = adapter
        tablayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewpager))
    return view
    }
}
