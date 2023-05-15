package com.assessment.phishsafe.homepagefeature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.assessment.phishsafe.R

class RecyclerAdapter(private var titles: List<String>, private var details: List<String>, private var images: List<Int>):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

            val contentTitle : TextView = itemView.findViewById(R.id.contentTitle)
            val contentDescription : TextView = itemView.findViewById(R.id.contentDescription)
            val contentImage : ImageView = itemView.findViewById(R.id.contentImage)

            init {
                itemView.setOnClickListener { v: View ->
                    val position: Int = adapterPosition
                    Toast.makeText(itemView.context, "You clicked on item ${position+1}", Toast.LENGTH_SHORT).show()
                }
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contentTitle.text = titles[position]
        holder.contentDescription.text = details[position]
        holder.contentImage.setImageResource(images[position])

    }


}