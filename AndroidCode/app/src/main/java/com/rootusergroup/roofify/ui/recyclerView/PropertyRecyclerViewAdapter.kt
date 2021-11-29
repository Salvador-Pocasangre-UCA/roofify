package com.rootusergroup.roofify.ui.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rootusergroup.roofify.MainActivity
import com.rootusergroup.roofify.R
import com.rootusergroup.roofify.data.entities.Post

class PropertyRecyclerViewAdapter : RecyclerView.Adapter<PropertyRecyclerViewAdapter.PropertyViewHolder>() {

    private var data: List<Post>? = null

    fun setData(data: List<Post>) {
        this.data = data
        notifyDataSetChanged()
    }

    class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(post: Post) {
            val titleTextView = itemView.findViewById<TextView>(R.id.myProperty_card_title)
            val priceTextView = itemView.findViewById<TextView>(R.id.myProperty_price_text1)
            titleTextView.text = post.title
            priceTextView.text = post.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_properties_item, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val data = data ?: return
        val post = data[position]
        holder.bind(post)

    }

    override fun getItemCount(): Int = data?.size ?: 0
}