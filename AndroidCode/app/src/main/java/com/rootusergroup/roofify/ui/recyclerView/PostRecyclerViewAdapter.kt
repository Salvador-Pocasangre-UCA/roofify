package com.rootusergroup.roofify.ui.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rootusergroup.roofify.R
import com.rootusergroup.roofify.data.entities.Post

class PostRecyclerViewAdapter : RecyclerView.Adapter<PostRecyclerViewAdapter.PostViewHolder>() {

    private var data: List<Post>? = null

    fun setData(data: List<Post>) {
        this.data = data
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(post: Post) {
            val titleTextView = itemView.findViewById<TextView>(R.id.myProperty_card_title)
            val priceTextView = itemView.findViewById<TextView>(R.id.myProperty_price_text1)
            titleTextView.text = post.title
            priceTextView.text = post.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.property_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val data = data ?: return
        val post = data[position]
        holder.bind(post)

    }

    override fun getItemCount(): Int = data?.size ?: 0
}