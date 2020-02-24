package com.nguyenxuansang.fastionshop.model

class Cart() {
    var idSp: String? = null
    var tenSp: String? = null
    var giaSp: String? = null
    var imgSp: String? = null
    var soLuongSp: Int = 0
    fun getNames(): String {
        return tenSp.toString()
    }

    fun setNames(name: String) {
        this.tenSp = name
    }
    fun getId(): String {
        return idSp.toString()
    }

    fun setId(id: String) {
        this.idSp = id
    }
    fun getGia(): String {
        return giaSp.toString()
    }

    fun setGia(name: String) {
        this.giaSp = name
    }
    fun getImg(): String {
        return imgSp.toString()
    }

    fun setImg(id: String) {
        this.imgSp = id
    }
    fun getSl(): Int {
        return soLuongSp
    }

    fun setSl(id: Int) {
        this.soLuongSp = id
    }

}