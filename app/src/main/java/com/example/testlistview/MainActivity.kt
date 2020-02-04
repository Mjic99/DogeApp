package com.example.testlistview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val DOGE_MESSAGE = "com.example.testlistview.MESSAGE"

class MainActivity : AppCompatActivity() {

    private lateinit var rvItems : RecyclerView
    private lateinit var adapter: DogeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvItems = findViewById(R.id.rv_items)
        rvItems.layoutManager = LinearLayoutManager(this)

        getDoges()
    }

    private val onDogeClickListener = View.OnClickListener { view ->
        val viewHolder = view.tag as ViewHolder
        val position = viewHolder.adapterPosition

        val selectedDoge: Doge = adapter.listItems[position]

        val intent = Intent(this, DisplayDogeActivity::class.java).apply {
            putExtra(DOGE_MESSAGE, selectedDoge)
        }
        startActivity(intent)
    }

    private fun getDoges() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val dogeApi : DogeApi = retrofit.create(DogeApi::class.java)

        val call : Call<List<Doge>> = dogeApi.getDoges()

        call.enqueue(object : Callback<List<Doge>> {
            override fun onResponse(call: Call<List<Doge>>?, response: Response<List<Doge>>?) {
                if (!response?.isSuccessful!!) {
                    Log.v("xd", response.code().toString())
                }
                adapter = DogeAdapter(response.body()!!)
                rvItems.adapter = adapter
                adapter.onItemClickListener = onDogeClickListener
            }

            override fun onFailure(call: Call<List<Doge>>?, t: Throwable?) {
                Log.v("xd", t?.message!!)
            }

        })
    }
}
