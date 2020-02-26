package com.nguyenxuansang.fastionshop.interfaces

import com.nguyenxuansang.fastionshop.model.*
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

    @POST("getaccount.php")
    @FormUrlEncoded
    fun getAccount(@Field("Id_Account") Id_Account :String) : Call<ArrayList<Account>>

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

    @POST("like.php")
    @FormUrlEncoded
    fun checkLike(@Field("Id_Account") Id_Account:String,
              @Field("Id_Fashion") Id_Fashion:String) : Call<ArrayList<Like>>

    @POST("yeuthichcuatoi.php")
    @FormUrlEncoded
    fun favorite(@Field("Id_Account") Id_Account:String) : Call<ArrayList<Favorite>>

    @POST("unvisiblefavorite.php")
    @FormUrlEncoded
    fun unVisiableFavorite(@Field("CheckLike") CheckLike:String,
              @Field("Id_Account") Id_Account:String, @Field("Id_Fashion") Id_Fashion:String) : Call<ArrayList<SignUpMessage>>

    @POST("diachinhanhang.php")
    @FormUrlEncoded
    fun goodsOrders(@Field("Id_Account") Id_Account:String,
        @Field("CustomerName") CustomerName:String,
                    @Field("PhoneNumber") PhoneNumber:String,
                    @Field("Address") Address:String,
                    @Field("DateOfPurchase") DateOfPurchase:String,
                    @Field("TotalMoney") TotalMoney:String) : Call<ArrayList<SignUpMessage>>

    @POST("visiblefavorite.php")
    @FormUrlEncoded
    fun visiableFavorite(@Field("Id_Account") Id_Account:String,
                    @Field("Id_Fashion") Id_Fashion:String,
                    @Field("NameFashion") NameFashion:String,
                    @Field("ImgFashion") ImgFashion:String,
                    @Field("PriceFashion") PriceFashion:String,
                    @Field("CheckLike") CheckLike:String) : Call<ArrayList<SignUpMessage>>

    @POST("chitietdonhang.php")
    @FormUrlEncoded
    fun orderDetails(@Field("Id_GoodSorders") Id_GoodSorders:String,
                    @Field("Id_Fashion") Id_Fashion:String,
                    @Field("NameFashion") NameFashion:String,
                    @Field("Number") Number:String,
                    @Field("Size") Size:String,
                     @Field("TotalMoney") TotalMoney:String) : Call<ArrayList<SignUpMessage>>
    companion object{
        var BASE_URL = "http://192.168.5.108/data_fashion_shop/"
        fun create():ApiInterface{
            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
                BASE_URL).build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}