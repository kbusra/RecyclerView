package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.MyAdapter
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.model.Data
import com.model.Reqres
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dataList:MutableList<Data> = mutableListOf()
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setup adapter
        myAdapter = MyAdapter(dataList)

        //setup recyclerview

        my_recycler_view.layoutManager=LinearLayoutManager(this)
        my_recycler_view.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        my_recycler_view.adapter = myAdapter

        // setup Android Networking
        AndroidNetworking.initialize(this)

        AndroidNetworking.get("https://reqres.in/api/users?page=2")
                .build()
                .getAsObject(Reqres::class.java, object : ParsedRequestListener<Reqres>{
                    override fun onError(anError: ANError?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(response: Reqres) {
                        dataList.addAll(response.data)
                        myAdapter.notifyDataSetChanged()
                    }

                })
    }
}
