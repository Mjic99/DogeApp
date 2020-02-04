package com.example.testlistview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DogeAdapter(dogeList : List<Doge>) : RecyclerView.Adapter<DogeAdapter.DogeViewHolder>() {

    var listItems : List<Doge> = dogeList
    lateinit var onItemClickListener : View.OnClickListener

    inner class DogeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dogeImage : ImageView = itemView.findViewById(R.id.foto)
        private val titulo : TextView = itemView.findViewById(R.id.titulo)
        private val descripcion : TextView = itemView.findViewById(R.id.descripcion)

        init {
            itemView.tag = this
            itemView.setOnClickListener(onItemClickListener)
        }

        fun setData(doge: Doge) {
            GlideApp.with(itemView).load(doge.imagen).into(dogeImage)
            titulo.text = doge.titulo
            descripcion.text = doge.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogeViewHolder {
        val dogeView : View = LayoutInflater.from(parent.context).inflate(R.layout.item, null, false)
        return DogeViewHolder(dogeView)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: DogeViewHolder, position: Int) {
        holder.setData(listItems[position])
    }
}