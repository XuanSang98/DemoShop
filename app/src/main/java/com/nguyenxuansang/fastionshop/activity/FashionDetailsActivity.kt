package com.nguyenxuansang.fastionshop.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import com.bumptech.glide.Glide
import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.adapter.BestSellerAdapter
import com.nguyenxuansang.fastionshop.interfaces.ApiInterface
import com.nguyenxuansang.fastionshop.model.Cart
import com.nguyenxuansang.fastionshop.model.Fashion
import kotlinx.android.synthetic.main.activity_fashion_details.*
import kotlinx.android.synthetic.main.activity_toolbar.*
import retrofit2.Call
import retrofit2.Response

class FashionDetailsActivity : AppCompatActivity() {
    var idsp:String?=null
    var img:String?=null
    var arr:ArrayList<Fashion> = arrayListOf()
    companion object{
        var size:String ? =null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fashion_details)
        setSupportActionBar(tb_fashion_Detail)
        size==null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back1)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        var dem:Int =1
        val ID_Fashion = intent.getStringExtra("ID_Fashion")
        Log.d("LOG_INTENT","myValue "+ID_Fashion)
        idsp = ID_Fashion.toString()
        val api_chitietsanpham = ApiInterface.create().getFashionDetail(""+ID_Fashion)
        api_chitietsanpham.enqueue(object : retrofit2.Callback<ArrayList<Fashion>> {
            override fun onFailure(call: Call<ArrayList<Fashion>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<Fashion>>,
                response: Response<ArrayList<Fashion>>
            ) {
                if (response.body() != null) {
                    arr = response.body()!!
                    Glide.with(applicationContext).load(""+arr.get(0).Img_Fasshion).into(img_fashion_detail)
                    txt_giamoi_spchitiet.text = arr.get(0).Price
                    txt_tenspchitiet.text = arr.get(0).Name_Fashion
                    txt_giacu_spchitiet.text = arr.get(0).PriceOld
                    img = arr.get(0).Img_Fasshion
                }else{
                    Toast.makeText(applicationContext,"Loi",Toast.LENGTH_LONG).show()
                }
            }
        })
        linearLayout.visibility = View.INVISIBLE
        img_fashion_detail.setOnClickListener {
            if(linearLayout.visibility == View.INVISIBLE)
            linearLayout.visibility = View.VISIBLE
            else
                linearLayout.visibility = View.INVISIBLE
        }

        btn_s.setOnClickListener {
            btn_s.setBackgroundColor(Color.GRAY)
            btn_s.setTextColor(Color.WHITE)
            btn_m.setBackgroundColor(Color.WHITE)
            btn_l.setBackgroundColor(Color.WHITE)
            btn_xl.setBackgroundColor(Color.WHITE)
            btn_l.setTextColor(Color.BLACK)
            btn_m.setTextColor(Color.BLACK)
            btn_xl.setTextColor(Color.BLACK)
            size = "S"
        }
        btn_m.setOnClickListener {
            btn_m.setBackgroundColor(Color.GRAY)
            btn_m.setTextColor(Color.WHITE)
            btn_s.setBackgroundColor(Color.WHITE)
            btn_l.setBackgroundColor(Color.WHITE)
            btn_xl.setBackgroundColor(Color.WHITE)
            btn_l.setTextColor(Color.BLACK)
            btn_s.setTextColor(Color.BLACK)
            btn_xl.setTextColor(Color.BLACK)
            size = "M"
        }
        btn_l.setOnClickListener {
            btn_l.setBackgroundColor(Color.GRAY)
            btn_l.setTextColor(Color.WHITE)
            btn_s.setBackgroundColor(Color.WHITE)
            btn_m.setBackgroundColor(Color.WHITE)
            btn_xl.setBackgroundColor(Color.WHITE)
            btn_s.setTextColor(Color.BLACK)
            btn_m.setTextColor(Color.BLACK)
            btn_xl.setTextColor(Color.BLACK)
            size = "L"
        }
        btn_xl.setOnClickListener {
            btn_xl.setBackgroundColor(Color.GRAY)
            btn_xl.setTextColor(Color.WHITE)
            btn_s.setBackgroundColor(Color.WHITE)
            btn_m.setBackgroundColor(Color.WHITE)
            btn_l.setBackgroundColor(Color.WHITE)
            btn_m.setTextColor(Color.BLACK)
            btn_s.setTextColor(Color.BLACK)
            btn_l.setTextColor(Color.BLACK)
            size = "XL"
        }
        var check  = false
        btn_muangay.setOnClickListener {
            if(size==null){
                Toast.makeText(applicationContext,"Bạn vui lòng chọn Size để tiếp tục mua hàng",Toast.LENGTH_LONG).show()
            }else{
                if(MainActivity.arr_cart!!.size>0){
                    for(i in 0 until MainActivity.arr_cart!!.size){
                        if(MainActivity.arr_cart!!.get(i).idSp == ID_Fashion){
                            MainActivity.arr_cart!!.get(i).soLuongSp = MainActivity.arr_cart!!.get(i).soLuongSp + 1
                            if(MainActivity.arr_cart!!.get(i).soLuongSp >=10){
                                MainActivity.arr_cart!!.get(0).soLuongSp = 10
                            }
                            check = true
                        }
                    }
                    if(check == false){
                        val imageModel = Cart()
                        imageModel.setNames(txt_tenspchitiet.text.toString())
                        imageModel.setId(idsp.toString())
                        imageModel.setImg(img.toString())
                        imageModel.setGia(txt_giamoi_spchitiet.text.toString())
                        imageModel.setSl(1)
                        imageModel.setSizes(size.toString())
                        MainActivity.arr_cart!!.add(imageModel)
                    }
                }else{
                    val imageModel = Cart()
                    imageModel.setNames(txt_tenspchitiet.text.toString())
                    imageModel.setId(idsp.toString())
                    imageModel.setImg(img.toString())
                    imageModel.setGia(txt_giamoi_spchitiet.text.toString())
                    imageModel.setSl(1)
                    imageModel.setSizes(size.toString())
                    MainActivity.arr_cart!!.add(imageModel)
                }
                val intent = Intent(this,CartActivity::class.java)
                startActivity(intent)
            }
        }
        tb_fashion_Detail.setNavigationOnClickListener {
            finish()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_actionbar_fsdetail, menu)
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
