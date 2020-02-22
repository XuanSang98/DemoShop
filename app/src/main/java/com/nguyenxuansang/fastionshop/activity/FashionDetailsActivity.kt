package com.nguyenxuansang.fastionshop.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.adapter.BestSellerAdapter
import com.nguyenxuansang.fastionshop.interfaces.ApiInterface
import com.nguyenxuansang.fastionshop.model.Fashion
import kotlinx.android.synthetic.main.activity_fashion_details.*
import retrofit2.Call
import retrofit2.Response

class FashionDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fashion_details)
        var dem:Int =1
        var arr:ArrayList<Fashion>
        val ID_Fashion = intent.getStringExtra("ID_Fashion")
        Log.d("LOG_INTENT","myValue "+ID_Fashion)
        val apinterface1 = ApiInterface.create().getFashionDetail(""+ID_Fashion)
        apinterface1.enqueue(object : retrofit2.Callback<ArrayList<Fashion>> {
            override fun onFailure(call: Call<ArrayList<Fashion>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<Fashion>>,
                response: Response<ArrayList<Fashion>>
            ) {
                if (response.body() != null) {
                    arr = response.body()!!
                    Glide.with(applicationContext).load(""+arr.get(0).Img_Fasshion).into(img_fashion_detail)
                    txt_name_detail.text = arr.get(0).Name_Fashion
                    txt_price_detail.text = arr.get(0).Price
                }else{
                    Toast.makeText(applicationContext,"Loi",Toast.LENGTH_LONG).show()
                }
            }
        })
        btn_s_fashion_detail.setOnClickListener {
            btn_s_fashion_detail.setBackgroundColor(Color.RED)
            btn_s_fashion_detail.setTextColor(Color.WHITE)
            btn_m_fashion_detail.setBackgroundColor(Color.WHITE)
            btn_l_fashion_detail.setBackgroundColor(Color.WHITE)
            btn_xl_fashion_detail.setBackgroundColor(Color.WHITE)
            btn_l_fashion_detail.setTextColor(Color.BLACK)
            btn_m_fashion_detail.setTextColor(Color.BLACK)
            btn_xl_fashion_detail.setTextColor(Color.BLACK)
        }
        btn_m_fashion_detail.setOnClickListener {
            btn_m_fashion_detail.setBackgroundColor(Color.RED)
            btn_m_fashion_detail.setTextColor(Color.WHITE)
            btn_s_fashion_detail.setBackgroundColor(Color.WHITE)
            btn_l_fashion_detail.setBackgroundColor(Color.WHITE)
            btn_xl_fashion_detail.setBackgroundColor(Color.WHITE)
            btn_l_fashion_detail.setTextColor(Color.BLACK)
            btn_s_fashion_detail.setTextColor(Color.BLACK)
            btn_xl_fashion_detail.setTextColor(Color.BLACK)
        }
        btn_l_fashion_detail.setOnClickListener {
            btn_l_fashion_detail.setBackgroundColor(Color.RED)
            btn_l_fashion_detail.setTextColor(Color.WHITE)
            btn_s_fashion_detail.setBackgroundColor(Color.WHITE)
            btn_m_fashion_detail.setBackgroundColor(Color.WHITE)
            btn_xl_fashion_detail.setBackgroundColor(Color.WHITE)
            btn_s_fashion_detail.setTextColor(Color.BLACK)
            btn_m_fashion_detail.setTextColor(Color.BLACK)
            btn_xl_fashion_detail.setTextColor(Color.BLACK)
        }
        btn_xl_fashion_detail.setOnClickListener {
            btn_xl_fashion_detail.setBackgroundColor(Color.RED)
            btn_xl_fashion_detail.setTextColor(Color.WHITE)
            btn_s_fashion_detail.setBackgroundColor(Color.WHITE)
            btn_m_fashion_detail.setBackgroundColor(Color.WHITE)
            btn_l_fashion_detail.setBackgroundColor(Color.WHITE)
            btn_m_fashion_detail.setTextColor(Color.BLACK)
            btn_s_fashion_detail.setTextColor(Color.BLACK)
            btn_l_fashion_detail.setTextColor(Color.BLACK)
        }
        btn_head_fashion_detail.setOnClickListener {
            btn_head_fashion_detail.setBackgroundResource(R.drawable.heart2)
            dem = dem+1
            if (dem %2==0){
                btn_head_fashion_detail.setBackgroundResource(R.drawable.heart1)
            }else{
                btn_head_fashion_detail.setBackgroundResource(R.drawable.heart2)
            }
        }
        btn_addtocart.setOnClickListener {
            val intent = Intent(this,CartActivity::class.java)
            startActivity(intent)
        }
    }
}
