package com.nguyenxuansang.fastionshop.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.adapter.BestSellerAdapter
import com.nguyenxuansang.fastionshop.interfaces.ApiInterface
import com.nguyenxuansang.fastionshop.model.Fashion
import retrofit2.Call
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class DressFragment : Fragment() {
    private lateinit var bestSellerAdapter:BestSellerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_dress, container, false)
        val recyclerview_dress= view.findViewById<RecyclerView>(R.id.recyclerview_dress) //id RecyclerView
        recyclerview_dress.layoutManager = GridLayoutManager(context, 2)
        val apinterface1 = ApiInterface.create().getDress()
        apinterface1.enqueue(object : retrofit2.Callback<ArrayList<Fashion>>{
            override fun onFailure(call: Call<ArrayList<Fashion>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<Fashion>>,
                response: Response<ArrayList<Fashion>>
            ) {
                if(response.body() != null)
                    bestSellerAdapter = response.body()?.let { context?.let { it1 ->
                        BestSellerAdapter(it,
                            it1
                        )
                    } }!!
                recyclerview_dress.adapter = bestSellerAdapter
            }
        })
        return view
    }
}
