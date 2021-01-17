package com.example.movieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private var data: ApiResult) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name:TextView = view.findViewById(R.id.name)
        val profession: TextView = view.findViewById(R.id.profession)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(view)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data1 = data.get(position)
        holder.name.text = data1.name
        holder.profession.text = data1.profession

    }

    override fun getItemCount(): Int {
        return data.size
    }
}