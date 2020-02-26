package com.nguyenxuansang.fastionshop.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.fragment.HomeFragment
import com.nguyenxuansang.fastionshop.interfaces.ApiInterface
import com.nguyenxuansang.fastionshop.interfaces.Server
import com.nguyenxuansang.fastionshop.model.GoodsOrder
import com.nguyenxuansang.fastionshop.model.SignUpMessage
import kotlinx.android.synthetic.main.activity_customer_information.*
import retrofit2.Call
import retrofit2.Callback
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CustomerInformation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_information)
        var c = Calendar.getInstance();
        var sdf = SimpleDateFormat("MM-dd-yyyy");
        var strDate:String = sdf.format(c.getTime());
        btn_giaodendiachinay.setOnClickListener {
            val api_diachinhanhang = ApiInterface.create().goodsOrders(edt_tennguoinhan.text.toString(),edt_sdtnguoinhan.text.toString(),edt_diachinhanhang.text.toString(),strDate,"10000")
            api_diachinhanhang.enqueue(object :Callback<ArrayList<SignUpMessage>>{
                override fun onFailure(call: Call<ArrayList<SignUpMessage>>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<ArrayList<SignUpMessage>>,
                    response: retrofit2.Response<ArrayList<SignUpMessage>>
                ) {
                    var arr:ArrayList<SignUpMessage> = arrayListOf()
                    arr = response.body()!!
                    Log.e("TAH",""+arr.get(0).mess)
                    for(i in 0 until MainActivity.arr_cart!!.size){
                        val api_chitietdonhang = ApiInterface.create().orderDetails(arr.get(0).mess,MainActivity.arr_cart!!.get(i).getId(),MainActivity.arr_cart!!.get(i).getNames(),MainActivity.arr_cart!!.get(i).soLuongSp.toString(),MainActivity.arr_cart!!.get(i).size.toString(),""+(MainActivity.arr_cart!!.get(i).soLuongSp)*(MainActivity.arr_cart!!.get(i).giaSp.toString().toInt()))
                        api_chitietdonhang.enqueue(object  : Callback<ArrayList<SignUpMessage>>{
                            override fun onFailure(call: Call<ArrayList<SignUpMessage>>, t: Throwable) {

                            }

                            override fun onResponse(
                                call: Call<ArrayList<SignUpMessage>>,
                                response: retrofit2.Response<ArrayList<SignUpMessage>>
                            ) {
                                MainActivity.arr_cart!!.clear()
                                val intent = Intent(applicationContext,HomeFragment::class.java)
                                startActivity(intent)
                            }
                        })
                    }
                }
            })
        }
    }
}
