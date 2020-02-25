package com.nguyenxuansang.fastionshop.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.adapter.CartAdapter
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {
    private lateinit var cartAdapter:CartAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setSupportActionBar(tb_cart)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back2)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        btn_buy.setOnClickListener {
            val intent = Intent(application,CustomerInformation::class.java)
            startActivity(intent)
        }
        tb_cart.setNavigationOnClickListener {
            finish()
        }
        val rce_cart= findViewById<RecyclerView>(R.id.recycler_cart) //id RecyclerView
        rce_cart.layoutManager = LinearLayoutManager(this)
        cartAdapter = CartAdapter(MainActivity.arr_cart!!,this)
        rce_cart.adapter = cartAdapter
    }
}