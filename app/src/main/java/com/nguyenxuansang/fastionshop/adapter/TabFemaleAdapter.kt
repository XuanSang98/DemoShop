package com.nguyenxuansang.fastionshop.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nguyenxuansang.fastionshop.fragment.*

class TabFemaleAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return DressFragment()
            }
            1 -> {
                return WomensClothesFragment()
            }
            2 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return WomenJacketFragment()
            }
            3 -> {
                return FemaleClothesFragment()
            }
            4 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return FemaleTrousserFragment()
            }
            5 -> {
                return SkirtFragment()
            }
            else -> return null
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title = ""
        when (position) {
            0 -> title = "DRESS"
            1 -> title = "WOMEN CLOTHER"
            2 -> title = "WOMEN JACKET"
            3 -> title = "FEMALE CLOTHES"
            4 -> title = "FEMALE TROUSSER"
            5 -> title = "SKIRT"
        }
        return title
    }
}