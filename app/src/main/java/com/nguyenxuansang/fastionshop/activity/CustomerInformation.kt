package com.nguyenxuansang.fastionshop.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.interfaces.ApiInterface
import com.nguyenxuansang.fastionshop.interfaces.Server
import com.nguyenxuansang.fastionshop.model.GoodsOrder
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
            api_diachinhanhang.enqueue(object :Callback<ArrayList<GoodsOrder>>{
                override fun onFailure(call: Call<ArrayList<GoodsOrder>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ArrayList<GoodsOrder>>,
                    response: retrofit2.Response<ArrayList<GoodsOrder>>
                ) {
                        Toast.makeText(applicationContext,"Mua Hàng Thành Công",Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
