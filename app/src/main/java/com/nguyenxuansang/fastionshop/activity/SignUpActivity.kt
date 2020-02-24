package com.nguyenxuansang.fastionshop.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.interfaces.ApiInterface
import com.nguyenxuansang.fastionshop.model.Account
import com.nguyenxuansang.fastionshop.model.SignUpMessage
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class SignUpActivity : AppCompatActivity() {
    var arr:ArrayList<SignUpMessage> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btn_signup.setOnClickListener {
            val apiSignUp = ApiInterface.create().signUp(txt_fullname_signup.text.toString(),txt_phone_signup.text.toString(),txt_email_signup.text.toString(),txt_pass_signup.text.toString(),"1",txt_address.text.toString())
            apiSignUp.enqueue(object : Callback<ArrayList<SignUpMessage>>{
                override fun onFailure(call: Call<ArrayList<SignUpMessage>>, t: Throwable) {
                    Log.e("SA",""+t.message)
                }

                override fun onResponse(
                    call: Call<ArrayList<SignUpMessage>>,
                    response: Response<ArrayList<SignUpMessage>>
                ) {
                    arr = response.body()!!
                   if(arr.get(0).mess.equals("Thanh Cong")){
                       Toast.makeText(application,"Đăng Ký Tài Khoản Thành Công",Toast.LENGTH_LONG).show()
                       val intent = Intent(application,LoginActivity::class.java)
                       startActivity(intent)
                   }else{
                       Toast.makeText(application,"Đăng Ký Tài Khoản Thất Bại",Toast.LENGTH_LONG).show()
                   }
                }
            })
        }
    }
}
