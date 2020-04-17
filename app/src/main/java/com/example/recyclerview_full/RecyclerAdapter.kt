package com.example.recyclerview_full

import android.graphics.drawable.AnimationDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(var animals: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerAdapter.animalViewHolder>(), Filterable {

    var animalsFull: ArrayList<String> = animals

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): animalViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return animalViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    override fun onBindViewHolder(holder: animalViewHolder, position: Int) {
        holder.textView.text = animals[position]
        holder.rowCountTextView.text = position.toString()

        val context = holder.mView.context
        holder.mView.setOnClickListener {
            Toast.makeText(context, animals.get(position), Toast.LENGTH_SHORT).show()
        }
        holder.mView.setOnLongClickListener {
            Toast.makeText(context, "You click long " + animals.get(position), Toast.LENGTH_SHORT)
                .show()
            animals.remove(animals.get(position))
            notifyDataSetChanged()
            true
        }
        holder.imageView.setOnClickListener {
            holder.wifiAnimation.isOneShot
        }
    }

    class animalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var wifiAnimation: AnimationDrawable
        var textView: TextView = itemView.findViewById(R.id.textView)
        var rowCountTextView: TextView = itemView.findViewById(R.id.textView2)
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
        val mView: View = itemView

        init {
            imageView.setBackgroundResource(R.drawable.animation)
            wifiAnimation = imageView.background as AnimationDrawable
        }
    }

    override fun getFilter(): Filter {
        return datafilter
    }

    private var datafilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<String> = ArrayList()
            if (constraint.toString().isEmpty()) {
                filteredList.addAll(animalsFull)
            } else {
                for (animal: String in animalsFull) {
                    if (animal.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(animal)
                    }
                }
            }
            val filterResults: FilterResults = FilterResults()
            filterResults.values = filteredList

            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            animals.clear()
            animals.addAll(results?.values as Collection<String>)
            notifyDataSetChanged()
        }

    }

}


