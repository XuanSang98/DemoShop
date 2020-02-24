package com.nguyenxuansang.fastionshop.model

import com.google.gson.annotations.SerializedName

class Account (
    @SerializedName("Id_Account")
    val idAccount:String,
    @SerializedName("FullName")
    val fullName:String,
    @SerializedName("PhoneNumber")
    val phoneNumber : String,
    @SerializedName("EmailAddress")
    val emailAddress : String,
    @SerializedName("Password")
    val passWord : String,
    @SerializedName("Address")
    val address : String,
    @SerializedName("AccountType")
    val accountType : String
)