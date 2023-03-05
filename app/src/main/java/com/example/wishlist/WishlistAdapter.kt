package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class WishlistAdapter(private val items: List<DisplayArticle>) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val item = items.get(position)
        // Set item views based on views and data model
        holder.linkTextView.text = item.link
        holder.priceTextView.text = item.price
        holder.nameTextView.text = item.itemName
    }
    override fun getItemCount(): Int {
        return items.size
    }





    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val linkTextView: TextView
        val priceTextView: TextView
        val nameTextView: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            linkTextView = itemView.findViewById(R.id.textView)
            priceTextView = itemView.findViewById(R.id.textView2)
            nameTextView = itemView.findViewById(R.id.textView3)
        }


    }
}