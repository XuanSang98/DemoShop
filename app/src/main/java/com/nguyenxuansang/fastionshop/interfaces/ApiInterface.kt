package com.nguyenxuansang.fastionshop.interfaces

import com.nguyenxuansang.fastionshop.model.Account
import com.nguyenxuansang.fastionshop.model.Fashion
import com.nguyenxuansang.fastionshop.model.SignUpMessage
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.ArrayList

interface ApiInterface {

    @GET("sanphambanchay.php")
    fun getBestSeller() : Call<ArrayList<Fashion>>

    @GET("thoitrangnu.php")
    fun getFashionFemale() : Call<ArrayList<Fashion>>

    @GET("thoitrangnam.php")
    fun getFashionMale() : Call<ArrayList<Fashion>>

    @GET("dress.php")
    fun getDress() : Call<ArrayList<Fashion>>

    @POST("chitietsanpham.php")
    @FormUrlEncoded
    fun getFashionDetail(@Field("ID_Fashion") ID_Fashion:String) : Call<ArrayList<Fashion>>

    @POST("chitietdanhmuc.php")
    @FormUrlEncoded
    fun getCatelogDetail(@Field("ID_StylishDetails") ID_StylishDetails:String) : Call<ArrayList<Fashion>>

    @POST("dangkytaikhoan.php")
    @FormUrlEncoded
    fun signUp(@Field("FullName") FullName:String,
               @Field("PhoneNumber") PhoneNumber:String,
               @Field("EmailAddress") EmailAddress:String,
               @Field("Password") Password:String,
               @Field("AccountType") AccountType:String,
               @Field("Address") Address:String) : Call<ArrayList<SignUpMessage>>

    @POST("dangnhap.php")
    @FormUrlEncoded
    fun logIn(@Field("PhoneNumber") PhoneNumber:String,
              @Field("Password") Password:String) : Call<ArrayList<Account>>

    companion object{
        var BASE_URL = "http://192.168.2.221/data_fashion_shop/"
        fun create():ApiInterface{
            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
                BASE_URL).build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}