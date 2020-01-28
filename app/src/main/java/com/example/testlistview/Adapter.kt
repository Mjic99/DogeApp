package com.example.testlistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Adapter(var context: Context, var listItems: List<Doge>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val item : Doge = getItem(position) as Doge

        val view : View = LayoutInflater.from(context).inflate(R.layout.item, null)

        val imagen : ImageView = view.findViewById(R.id.foto)
        val titulo : TextView = view.findViewById(R.id.titulo)
        val descripcion : TextView = view.findViewById(R.id.descripcion)

        GlideApp.with(view).load(item.imagen).into(imagen)
        titulo.setText(item.titulo)
        descripcion.setText(item.description)

        return view
    }

    override fun getItem(position: Int): Any {
        return listItems.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listItems.size
    }

}