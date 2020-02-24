package com.nguyenxuansang.fastionshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.activity.MainActivity
import com.nguyenxuansang.fastionshop.interfaces.BestSellerOnClick
import com.nguyenxuansang.fastionshop.model.Cart

class CartAdapter (var items : ArrayList<Cart>, val context: Context

) : RecyclerView.Adapter<CartAdapter.ViewHolder>(){

    lateinit var clickListener : BestSellerOnClick

    fun setOnClickListener(mClickListener :BestSellerOnClick){
        clickListener = mClickListener
    }

    fun setMovieListItems(movieList: ArrayList<Cart>){
        this.items = movieList;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_tensp.text = items.get(position).tenSp
        holder.txt_giasp.text = items.get(position).giaSp
        holder.txt_soluong.text = items.get(position).soLuongSp.toString()
        Glide.with(context).load(items.get(position).imgSp).into(holder.img_giohang)
        holder.itemView.setOnClickListener {
            //activity dau
          //  clickListener.onClick(position = position, name = this.items[position].ID_Fashion)
            //rooi
        }
        var soLuong:Int = holder.txt_soluong.text.toString().toInt()
        if(soLuong>=10){
            holder.btn_cong.visibility = View.INVISIBLE
            holder.btn_tru.visibility = View.VISIBLE
        } else if (soLuong <= 1) {
            holder.btn_cong.visibility = View.VISIBLE
            holder.btn_tru.visibility = View.INVISIBLE
        } else if (soLuong >= 1) {
            holder.btn_cong.visibility = View.VISIBLE
            holder.btn_tru.visibility = View.VISIBLE
        }
        holder.btn_cong.setOnClickListener {
            var soLuongMoiNhat :Int = holder.txt_soluong.text.toString().toInt() + 1
            var soLuongHienTai:Int = MainActivity.arr_cart!!.get(position).soLuongSp
            MainActivity.arr_cart!!.get(position).setSl(soLuongMoiNhat)
            if(soLuongMoiNhat>9){
                holder.btn_cong.visibility = View.INVISIBLE
                holder.btn_tru.visibility = View.VISIBLE
                holder.txt_soluong.text = soLuongMoiNhat.toString()
            }else {
                holder.btn_cong.visibility = View.VISIBLE
                holder.btn_tru.visibility = View.VISIBLE
                holder.txt_soluong.text = soLuongMoiNhat.toString()
            }
        }
        holder.btn_tru.setOnClickListener {
            var soLuongMoiNhat :Int = holder.txt_soluong.text.toString().toInt() - 1
            var soLuongHienTai:Int = MainActivity.arr_cart!!.get(position).soLuongSp
            MainActivity.arr_cart!!.get(position).setSl(soLuongMoiNhat)
            if(soLuongMoiNhat<2){
                holder.btn_cong.visibility = View.VISIBLE
                holder.btn_tru.visibility = View.INVISIBLE
                holder.txt_soluong.text = soLuongMoiNhat.toString()
            }else {
                holder.btn_cong.visibility = View.VISIBLE
                holder.btn_tru.visibility = View.VISIBLE
                holder.txt_soluong.text = soLuongMoiNhat.toString()
            }
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var img_giohang: ImageView
        var txt_tensp: TextView
        var txt_giasp: TextView
        var txt_soluong: TextView
        var btn_tru : Button
        var btn_cong : Button
        init {
            txt_tensp = view.findViewById(R.id.txt_name_cartdetail) as TextView
            img_giohang = view.findViewById(R.id.img_cart_item) as ImageView
            txt_soluong = view.findViewById(R.id.txt_number_cartdetail) as TextView
            txt_giasp = view.findViewById(R.id.txt_price_cartdetail) as TextView
            btn_tru = view.findViewById(R.id.btn_minus) as Button
            btn_cong = view.findViewById(R.id.btn_plus) as Button
        }
    }
}