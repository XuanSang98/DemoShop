package com.nguyenxuansang.fastionshop.model

import com.google.gson.annotations.SerializedName

data class Fashion(
    @SerializedName("ID_Fashion")
    val ID_Fashion: String,
    @SerializedName("Name_Fashion")
    val Name_Fashion: String,
    @SerializedName("Price")
    val Price: String,
    @SerializedName("Img_Fasshion")
    val Img_Fasshion: String
)
