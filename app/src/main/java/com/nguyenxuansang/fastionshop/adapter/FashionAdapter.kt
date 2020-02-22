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
import com.nguyenxuansang.fastionshop.model.Fashion
import kotlin.collections.ArrayList

class FashionAdapter (var items : ArrayList<Fashion>, val context: Context) : RecyclerView.Adapter<FashionAdapter.ViewHolder>() {

    fun setMovieListItems(movieList: ArrayList<Fashion>){
        this.items = movieList;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.newarrivals_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_newarrival.text = items.get(position).Name_Fashion
        Glide.with(context).load(items.get(position).Img_Fasshion).into(holder.img_newarrival)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
         var img_newarrival:ImageView
         var txt_newarrival:TextView

        init {
            txt_newarrival = view.findViewById(R.id.txt_newarrivals) as TextView
            img_newarrival = view.findViewById(R.id.img_newarrivals) as ImageView
        }
    }
}