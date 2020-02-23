package com.nguyenxuansang.fastionshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.interfaces.BestSellerOnClick
import com.nguyenxuansang.fastionshop.model.Product

class ProductAdapter(var items : ArrayList<Product>, val context: Context

) : RecyclerView.Adapter<ProductAdapter.ViewHolder>(){
    lateinit var clickListener : BestSellerOnClick

    fun setOnClickListener(mClickListener : BestSellerOnClick){
        clickListener = mClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_product.text = items.get(position).txt_product
        Glide.with(context).load(items.get(position).img_product).into(holder.img_product)
        holder.itemView.setOnClickListener {
            //activity dau
            clickListener.onClick(position = position, name = this.items[position].id_product.toString())
            //rooi
        }
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var img_product: ImageView
        var txt_product: TextView
        init {
            txt_product = view.findViewById(R.id.txt_product_item) as TextView
            img_product = view.findViewById(R.id.img_product_item) as ImageView

        }
    }
}