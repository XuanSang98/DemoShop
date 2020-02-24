package com.nguyenxuansang.fastionshop.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.adapter.FashionFemaleAdapter
import com.nguyenxuansang.fastionshop.interfaces.ApiInterface
import com.nguyenxuansang.fastionshop.interfaces.BestSellerOnClick
import com.nguyenxuansang.fastionshop.model.Fashion
import kotlinx.android.synthetic.main.activity_catalog_details.*
import retrofit2.Call
import retrofit2.Response

class CatalogDetailsActivity : AppCompatActivity(),BestSellerOnClick {
    override fun onClick(position: Int, name: String) {
        val intent = Intent(this,FashionDetailsActivity::class.java)
        intent.putExtra("ID_Fashion",""+name)
        startActivity(intent)
    }

    private lateinit var fashion: FashionFemaleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog_details)
        setSupportActionBar(tb_catalogdetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back2)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val ID_StylishDetails = intent.getStringExtra("ID_StylishDetails")
        val recycler_catalog= findViewById<RecyclerView>(R.id.recycler_catalog) //id RecyclerView
        recycler_catalog.layoutManager = GridLayoutManager(this, 2)
        val api_chitietdanhmuc = ApiInterface.create().getCatelogDetail(""+ID_StylishDetails)
        api_chitietdanhmuc.enqueue(object : retrofit2.Callback<ArrayList<Fashion>> {
            override fun onFailure(call: Call<ArrayList<Fashion>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<Fashion>>,
                response: Response<ArrayList<Fashion>>
            ) {
                if (response.body() != null) {
                    fashion = FashionFemaleAdapter(response.body()!!,application)
                    recycler_catalog.adapter = fashion
                    onClickFashion(fashion)
                }else{
                    Toast.makeText(applicationContext,"Loi",Toast.LENGTH_LONG).show()
                }
            }
        })
        tb_catalogdetail.setNavigationOnClickListener {
            finish()
        }
    }
    private fun onClickFashion(bestSellerAdapter: FashionFemaleAdapter) {
        bestSellerAdapter.setOnClickListener(this)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_actionbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_cart ->{
                val intent = Intent(this,CartActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}
