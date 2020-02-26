package com.nguyenxuansang.fastionshop.model

import com.google.gson.annotations.SerializedName

class Favorite (
    @SerializedName("Id_Favorite")
    val Id_Favorite:String,
    @SerializedName("Id_Account")
    val Id_Account:String,
    @SerializedName("Id_Fashion")
    val Id_Fashion : String,
    @SerializedName("NameFashion")
    val NameFashion : String,
    @SerializedName("ImgFashion")
    val ImgFashion : String,
    @SerializedName("PriceFashion")
    val PriceFashion : String,
    @SerializedName("CheckLike")
    val CheckLike : String
)