package com.nguyenxuansang.fastionshop.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ViewFlipper
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nguyenxuansang.fastionshop.R
import com.nguyenxuansang.fastionshop.activity.FashionDetailsActivity
import com.nguyenxuansang.fastionshop.adapter.BestSellerAdapter
import com.nguyenxuansang.fastionshop.adapter.FashionFemaleAdapter
import com.nguyenxuansang.fastionshop.adapter.FashionMaleAdapter
import com.nguyenxuansang.fastionshop.interfaces.ApiInterface
import com.nguyenxuansang.fastionshop.interfaces.BestSellerOnClick
import com.nguyenxuansang.fastionshop.model.Fashion
import retrofit2.Call
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), BestSellerOnClick {
    lateinit var slide:ViewFlipper
    private lateinit var bestSellerAdapter:BestSellerAdapter
    private lateinit var fashion_female:FashionFemaleAdapter
    private lateinit var fashion_male:FashionMaleAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_home, container, false)
        //custom slide
        var numbers = arrayOf(R.drawable.slide12,R.drawable.slide11,R.drawable.slide13)
        slide = view.findViewById(R.id.slide)
       for (i in 0 until numbers.size){
           slide(numbers[i])
       }
        slide.flipInterval =6000
        slide.isAutoStart = true
        var animation_in =AnimationUtils.loadAnimation(activity,R.anim.set_out_right)
        var animation_out =AnimationUtils.loadAnimation(activity,R.anim.slide_in_right)
        slide.setInAnimation(animation_in)
        slide.setOutAnimation(animation_out)
        //recyclerview selling
        val rce_selling= view.findViewById<RecyclerView>(R.id.recyclerbestseller) //id RecyclerView
        rce_selling.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val api_rce_selling = ApiInterface.create().getBestSeller()
        api_rce_selling.enqueue(object : retrofit2.Callback<ArrayList<Fashion>>{
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
                rce_selling.adapter = bestSellerAdapter
                setInterface(bestSellerAdapter)
            }
        })
        //recyclerview fashion female
        val rce_fashionfemale= view.findViewById<RecyclerView>(R.id.recyclerfemale) //id RecyclerView
        rce_fashionfemale.layoutManager = GridLayoutManager(context, 2)
        val apinterface1 = ApiInterface.create().getFashionFemale()
        apinterface1.enqueue(object : retrofit2.Callback<ArrayList<Fashion>>{
            override fun onFailure(call: Call<ArrayList<Fashion>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<Fashion>>,
                response: Response<ArrayList<Fashion>>
            ) {
                if(response.body() != null)
                    fashion_female = response.body()?.let { context?.let { it1 ->
                        FashionFemaleAdapter(it,
                            it1
                        )
                    } }!!
                rce_fashionfemale.adapter = fashion_female
                setOnClickFemale(fashion_female)
            }
        })
        //recyclerview fashion male
        val rce_fashionmale= view.findViewById<RecyclerView>(R.id.recyclermale) //id RecyclerView
        rce_fashionmale.layoutManager = GridLayoutManager(context, 2)
        val api_fashionmale = ApiInterface.create().getFashionMale()
        api_fashionmale.enqueue(object : retrofit2.Callback<ArrayList<Fashion>>{
            override fun onFailure(call: Call<ArrayList<Fashion>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<Fashion>>,
                response: Response<ArrayList<Fashion>>
            ) {
                if(response.body() != null)
                    fashion_male = response.body()?.let { context?.let { it1 ->
                        FashionMaleAdapter(it,
                            it1
                        )
                    } }!!
                rce_fashionmale.adapter = fashion_male
                setOnClickMale(fashion_male)
            }
        })
        return view
    }
    private fun setInterface(bestSellerAdapter: BestSellerAdapter) {
        bestSellerAdapter.setOnClickListener(this)
    }

    private fun setOnClickFemale(fashion_female:FashionFemaleAdapter) {
        fashion_female.setOnClickListener(this)
    }

    private fun setOnClickMale(fashion_male:FashionMaleAdapter) {
        fashion_male.setOnClickListener(this)
    }

    fun slide(image:Int){
        val imageView = ImageView(activity)
        imageView.setBackgroundResource(image)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        slide.addView(imageView)
    }
    override fun onClick(position: Int, name: String) {
        val intent = Intent(context,FashionDetailsActivity::class.java)
        intent.putExtra("ID_Fashion",""+name)
        startActivity(intent)
    }
}
