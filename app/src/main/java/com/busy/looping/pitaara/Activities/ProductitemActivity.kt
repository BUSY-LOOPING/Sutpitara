package com.busy.looping.pitaara.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.busy.looping.pitaara.Adpter.ProductItemAdpter
import com.busy.looping.pitaara.R
import com.busy.looping.pitaara.baseactivity.BaseActivity
import com.busy.looping.pitaara.databinding.ActivityProductitemBinding
import com.busy.looping.pitaara.gobal.Constance
import com.busy.looping.pitaara.models.SingleCategory
import com.busy.looping.pitaara.retrofit.RetrofitResponse
import com.busy.looping.pitaara.retrofit.URL
import com.google.gson.Gson
import com.google.gson.JsonObject

class ProductitemActivity : BaseActivity() {
   lateinit var binding:ActivityProductitemBinding
   lateinit var  itemAdpter: ProductItemAdpter;
    var itemList=ArrayList<SingleCategory>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProductitemBinding.inflate(layoutInflater)
        setContentView(binding.root)
           var jsonObject=JsonObject()
        callWb(this,Constance.BASE_URL+URL.GET_ALLPRODUCTSBYCATEGORY,Constance.GET,jsonObject,object :RetrofitResponse{
            override fun onResponse(response: String?, methodName: String?, responseCode: Int) {
              Toast.makeText(this@ProductitemActivity,response.toString(),Toast.LENGTH_SHORT).show()

                var infoitemList = Gson().fromJson(response,Array<SingleCategory>::class.java).asList()
                Log.d("TESTMODELISTTT====",infoitemList.toString())
                setItemRecyclviewAdpter(infoitemList)

            }

            override fun onResponseFail(methodName: String?, responseCode: Int) {

            }

        })


    }
    fun setItemRecyclviewAdpter(infoitemList: List<SingleCategory>)
    {
//       itemList.add(
//           ProductItemModel("https://i.pinimg.com/564x/b3/f2/6b/b3f26b0f5b2484cbbd6dd656faf09757.jpg",
//       "Product Name","4.5","10","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua")
//       )
//        itemList.add(
//            ProductItemModel("https://i.pinimg.com/564x/b3/f2/6b/b3f26b0f5b2484cbbd6dd656faf09757.jpg",
//            "Product Name","4.5","10","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua")
//        )
//        itemList.add(
//            ProductItemModel("https://i.pinimg.com/564x/b3/f2/6b/b3f26b0f5b2484cbbd6dd656faf09757.jpg",
//            "Product Name","4.5","10","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua")
//        )
//        itemList.add(
//            ProductItemModel("https://i.pinimg.com/564x/b3/f2/6b/b3f26b0f5b2484cbbd6dd656faf09757.jpg",
//            "Product Name","4.5","10","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua")
//        )
//        itemList.add(
//            ProductItemModel("https://i.pinimg.com/564x/b3/f2/6b/b3f26b0f5b2484cbbd6dd656faf09757.jpg",
//            "Product Name","4.5","10","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua")
//        )
        itemAdpter= ProductItemAdpter(this, infoitemList,object:ProductItemAdpter.ItemClickListener{
            override fun onItemListener(tag: Int) {
                super.onItemListener(tag)

                var tempmodel:SingleCategory=infoitemList[tag]
                var intent= Intent(applicationContext,Productdiscripiton::class.java)
                intent.putExtra("rating",tempmodel.rating.toString())
                intent.putExtra("descripation",tempmodel.des.toString())
                intent.putExtra("price",tempmodel.price.toString())
                startActivity(intent)
            }
        })
        val dividerItemDecoration = DividerItemDecoration(
            this,
            LinearLayoutManager.HORIZONTAL
        )
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.recyclview_divider))

        binding.recyclerViewItems.layoutManager=GridLayoutManager(this,2)
        binding.recyclerViewItems.addItemDecoration(dividerItemDecoration)
        binding.recyclerViewItems.adapter=itemAdpter
    }
}