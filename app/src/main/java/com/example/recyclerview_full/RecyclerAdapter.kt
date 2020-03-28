package com.example.recyclerview_full

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(var animals: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerAdapter.animalViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): animalViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return animalViewHolder(item)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    override fun onBindViewHolder(holder: animalViewHolder, position: Int) {
        holder.textView.text = animals[position]
        holder.rowCountTextView.text = position.toString()

    }

    class animalViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var textView: TextView
        var rowCountTextView: TextView

        init {
            textView = item.findViewById(R.id.textView)
            rowCountTextView = item.findViewById(R.id.textView2)

        }

    }

}