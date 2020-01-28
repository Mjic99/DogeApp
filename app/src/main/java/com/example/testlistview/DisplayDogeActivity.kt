package com.example.testlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DisplayDogeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_doge)

        val doge = intent.getSerializableExtra(DOGE_MESSAGE) as? Doge

        findViewById<TextView>(R.id.doge_title).text = doge?.titulo
        GlideApp.with(this).load(doge?.imagen).into(findViewById(R.id.doge_image))
        findViewById<TextView>(R.id.doge_desc).text = doge?.description
    }
}
