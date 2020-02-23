package com.nguyenxuansang.fastionshop.model

class Product() {
    var id_product :String ? = null
    var img_product :Int = 0
    var txt_product : String ? = null
    fun getNames(): String {
        return txt_product.toString()
    }

    fun setNames(name: String) {
        this.txt_product = name
    }
    fun getId(): String {
        return id_product.toString()
    }

    fun setId(id: String) {
        this.id_product = id
    }
    fun getImage_drawables(): Int {
        return img_product
    }

    fun setImage_drawables(image_drawable: Int) {
        this.img_product = image_drawable
    }
}