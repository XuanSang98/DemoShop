package com.nguyenxuansang.fastionshop.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.activity.CartActivity
import com.nguyenxuansang.fastionshop.activity.CatalogDetailsActivity
import com.nguyenxuansang.fastionshop.adapter.ProductAdapter
import com.nguyenxuansang.fastionshop.interfaces.BestSellerOnClick
import com.nguyenxuansang.fastionshop.model.Product

/**
 * A simple [Fragment] subclass.
 */
class ProductPortfolioFragment : Fragment() ,BestSellerOnClick{
    override fun onClick(position: Int, name: String) {
        val intent = Intent(context,CatalogDetailsActivity::class.java)
        intent.putExtra("ID_StylishDetails",""+name)
        startActivity(intent)
    }

    private lateinit var productAdapter:ProductAdapter
    private lateinit var productAdapter1:ProductAdapter
    private val img_product_female = intArrayOf(R.drawable.polo, R.drawable.aosomi, R.drawable.dam, R.drawable.chanvay, R.drawable.shorts,R.drawable.jeans ,R.drawable.pants)
    private val name_product_female = arrayOf("Áo Thun", "Áo Somi", "Đầm Nữ", "Chân Váy", "Quần Short", "Quần Jean", "Quần Thun")
    private val id_product_female = arrayOf("1", "2", "3", "4", "5", "6", "7")
    private val img_product_male = intArrayOf(R.drawable.polo, R.drawable.aosomi, R.drawable.shorts, R.drawable.jeans, R.drawable.kaki,R.drawable.pants ,R.drawable.quantay)
    private val name_product_male = arrayOf("Áo Thun", "Áo Somi", "Quần Short", "Quần Jean", "Quần Kaki", "Quần Jagger", "Quần Tây")
    private val id_product_male = arrayOf("8", "9", "10", "11", "12", "13", "14")
    private var arr_product_female: ArrayList<Product>? = null
    private var arr_product_male: ArrayList<Product>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_product_portfolio, container, false)
        arr_product_female = ListDanhMucNu()
        arr_product_male = ListDanhMucNam()
        val rce_product= view.findViewById<RecyclerView>(R.id.recycler_danhmucthoitrangnu) //id RecyclerView
        rce_product.layoutManager = GridLayoutManager(context, 3)
        productAdapter = this.context?.let { ProductAdapter(arr_product_female!!, it) }!!
        rce_product.adapter = productAdapter
        clickDanhMucNu(productAdapter)

        val rce_product_nam= view.findViewById<RecyclerView>(R.id.recycler_danhmucthoitrangnam) //id RecyclerView
        rce_product_nam.layoutManager = GridLayoutManager(context, 3)
        productAdapter1 = this.context?.let { ProductAdapter(arr_product_male!!, it) }!!
        rce_product_nam.adapter = productAdapter1
        clickDanhMucNu(productAdapter1)
        return view
    }
    private fun clickDanhMucNu(bestSellerAdapter: ProductAdapter) {
        bestSellerAdapter.setOnClickListener(this)
    }
    private fun clickDanhMucNam(bestSellerAdapter: ProductAdapter) {
        bestSellerAdapter.setOnClickListener(this)
    }
    private fun ListDanhMucNu(): ArrayList<Product> {

        val list = ArrayList<Product>()

        for (i in 0..6) {
            val imageModel = Product()
            imageModel.setNames(name_product_female[i])
            imageModel.setId(id_product_female[i])
            imageModel.setImage_drawables(img_product_female[i])
            list.add(imageModel)
        }
        return list
    }
    private fun ListDanhMucNam(): ArrayList<Product> {

        val list = ArrayList<Product>()

        for (i in 0..6) {
            val imageModel = Product()
            imageModel.setNames(name_product_male[i])
            imageModel.setId(id_product_male[i])
            imageModel.setImage_drawables(img_product_male[i])
            list.add(imageModel)
        }
        return list
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_cart ->{
                val intent = Intent(context, CartActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}
