package com.nguyenxuansang.fastionshop.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.activity.FashionDetailsActivity
import com.nguyenxuansang.fastionshop.activity.LoginActivity
import com.nguyenxuansang.fastionshop.adapter.FashionFemaleAdapter
import com.nguyenxuansang.fastionshop.adapter.FavoriteAdapter
import com.nguyenxuansang.fastionshop.interfaces.ApiInterface
import com.nguyenxuansang.fastionshop.interfaces.BestSellerOnClick
import com.nguyenxuansang.fastionshop.model.Favorite
import retrofit2.Call
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() , BestSellerOnClick {
    override fun onClick(position: Int, name: String) {
        val intent = Intent(context, FashionDetailsActivity::class.java)
        intent.putExtra("ID_Fashion",""+name)
        startActivity(intent)
    }

    private lateinit var fashion_female: FavoriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_favorite, container, false)
        var arr:ArrayList<Favorite>
        val rce_fashionfemale= view.findViewById<RecyclerView>(R.id.recycler_favorite) //id RecyclerView
        rce_fashionfemale.layoutManager = GridLayoutManager(context, 2)
        val apinterface1 = ApiInterface.create().favorite(LoginActivity.id_account.toString())
        apinterface1.enqueue(object : retrofit2.Callback<ArrayList<Favorite>>{
            override fun onFailure(call: Call<ArrayList<Favorite>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<Favorite>>,
                response: Response<ArrayList<Favorite>>
            ) {
                fashion_female = response.body()?.let { context?.let { it1 ->
                    FavoriteAdapter(it,
                        it1
                    )
                } }!!
                rce_fashionfemale.adapter = fashion_female
                setOnClickFemale(fashion_female)
            }
        })
        return view
    }
    private fun setOnClickFemale(fashion_female:FavoriteAdapter) {
        fashion_female.setOnClickListener(this)
    }


}
