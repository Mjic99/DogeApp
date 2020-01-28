package com.example.testlistview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
//import sun.jvm.hotspot.utilities.IntArray


const val DOGE_MESSAGE = "com.example.testlistview.MESSAGE"

class MainActivity : AppCompatActivity() {

    private lateinit var lvItems : ListView
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvItems = findViewById(R.id.lv_items)

        getDoges()

        lvItems.setOnItemClickListener { parent, view, position, id ->

            val selectedDoge = parent.adapter.getItem(position) as Doge

            val intent = Intent(this, DisplayDogeActivity::class.java).apply {
                putExtra(DOGE_MESSAGE, selectedDoge)
            }
            startActivity(intent)
        }
    }

    private fun createAdapter(list: List<Doge>) {
        adapter = Adapter(this, list)
        lvItems.adapter = adapter
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
                createAdapter(response.body()!!)
            }

            override fun onFailure(call: Call<List<Doge>>?, t: Throwable?) {
                Log.v("xd", t?.message!!)
            }

        })
    }
}
