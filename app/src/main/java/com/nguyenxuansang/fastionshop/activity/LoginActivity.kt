package com.nguyenxuansang.fastionshop.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.interfaces.ApiInterface
import com.nguyenxuansang.fastionshop.model.Account
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    var arr:ArrayList<Account> = arrayListOf()
    companion object{
        var id_account :String ?= null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(tb_login)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        login_main.setOnClickListener {
            val apiLogin = ApiInterface.create().logIn(edtSdt.text.toString(),txt_pass.text.toString())
            apiLogin.enqueue(object :Callback<ArrayList<Account>>{
                override fun onFailure(call: Call<ArrayList<Account>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ArrayList<Account>>,
                    response: Response<ArrayList<Account>>
                ) {
                    arr = response.body()!!
                    if(arr.get(0).phoneNumber.equals(edtSdt.text.toString()) && arr.get(0).passWord.equals(txt_pass.text.toString())){
                        val intent = Intent(application,MainActivity::class.java)
                        startActivity(intent)
                        id_account = arr.get(0).idAccount
                    }else{
                        Toast.makeText(application,"Tên tài khoản hoặc mật khẩu không chính xác",Toast.LENGTH_LONG).show()
                    }
                }
            })

        }
        txt_signup.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
