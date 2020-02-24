package com.nguyenxuansang.fastionshop.model

import com.google.gson.annotations.SerializedName

data class SignUpMessage (
    @SerializedName("message")
    val mess:String
)