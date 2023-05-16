package com.assessment.phishsafe.homepagefeature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
// import android.widget.ImageView for image implementation later
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assessment.phishsafe.R


// Recycler view of the content feature, dynamically generating activities from the fetched
// title and description entries of the database
// These rows then are clickable and take the user to the content page
class RecyclerAdapter(private var contentList: List<AwarenessContent>, private val onItemClick: (AwarenessContent) -> Unit): //, private var images: List<Int>
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {



        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

            val contentTitle : TextView = itemView.findViewById(R.id.contentTitle)
            val contentDescription : TextView = itemView.findViewById(R.id.contentDescription)
            //val contentImage : ImageView = itemView.findViewById(R.id.contentImage)

            // When clicked on any dynamically created listing, it calls the onItemClick which takes
            // the user to the generated page with the content on it.
            init {
                itemView.setOnClickListener {
                    val position: Int = adapterPosition
                    onItemClick(contentList[position])

                }
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    // Number of rows in dynamically listed contents
    override fun getItemCount(): Int {
        return contentList.size
    }

    // Sets the fetched title and description
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contentItem = contentList[position]
        holder.contentTitle.text = contentItem.title
        holder.contentDescription.text = contentItem.description
        //holder.contentImage.setImageResource(images[position])

    }

    // Sets content list, this is used at the observing for the list, which if changed, sets the
    // recycler view right
    fun setContentList(list: List<AwarenessContent>) {
        contentList = list
        notifyDataSetChanged()
    }


}