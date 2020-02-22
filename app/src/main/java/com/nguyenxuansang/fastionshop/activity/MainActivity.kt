package com.nguyenxuansang.fastionshop.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.R.id.menu_home
import com.nguyenxuansang.fastionshop.fragment.HomeFragment
import com.nguyenxuansang.fastionshop.fragment.MemaleFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_toolbar.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val fragment = HomeFragment()
    val fragmentFemale = MemaleFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        init()
        appbar.bringToFront()
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        supportActionBar?.setHomeButtonEnabled(true)
        tb.setText("Home")
    }
    private fun init(){
        val toggle = ActionBarDrawerToggle(Activity(),drawer_layout,toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        navigation_view.setNavigationItemSelectedListener(this)
        navigateToFragment(fragment)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_actionbar, menu)
        return true
    }
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            menu_home ->{
                navigateToFragment(fragment)
                tb.setText("Home")
            }
            R.id.menu_male ->{

            }
            R.id.menu_female ->{
                navigateToFragment(fragmentFemale)
                tb.setText("Female")
            }
            R.id.menu_favorites ->{

            }
        }
        p0.isChecked = true
        drawer_layout.closeDrawers()
        return true
    }
    private fun navigateToFragment(fragmentToNavigate: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_main, fragmentToNavigate)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}

