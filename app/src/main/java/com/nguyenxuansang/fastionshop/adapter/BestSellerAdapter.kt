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
import com.nguyenxuansang.fastionshop.model.Fashion
import kotlin.collections.ArrayList

class BestSellerAdapter (var items : ArrayList<Fashion>, val context: Context

) : RecyclerView.Adapter<BestSellerAdapter.ViewHolder>(){

    lateinit var clickListener : BestSellerOnClick

    fun setOnClickListener(mClickListener :BestSellerOnClick){
        clickListener = mClickListener
    }

    fun setMovieListItems(movieList: ArrayList<Fashion>){
        this.items = movieList;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.best_sellers_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_bestseller.text = items.get(position).Name_Fashion
        Glide.with(context).load(items.get(position).Img_Fasshion).into(holder.img_bestseller)
        holder.itemView.setOnClickListener {
            //activity dau
            clickListener.onClick(position = position, name = this.items[position].ID_Fashion)
            //rooi
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var img_bestseller:ImageView
        var txt_bestseller:TextView
        init {
            txt_bestseller = view.findViewById(R.id.txt_bestseller) as TextView
            img_bestseller = view.findViewById(R.id.img_bestseller) as ImageView

        }

    }


}