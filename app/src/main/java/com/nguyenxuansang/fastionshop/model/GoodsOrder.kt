package com.nguyenxuansang.fastionshop.model

import com.google.gson.annotations.SerializedName

class GoodsOrder (
    @SerializedName("CustomerName")
    val customerName:String,
    @SerializedName("PhoneNumber")
    val phoneNumber:String,
    @SerializedName("Address")
    val address : String,
    @SerializedName("DateOfPurchase")
    val dateOfPurchase : String,
    @SerializedName("TotalMoney")
    val totalMoney : String
)