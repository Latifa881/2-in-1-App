package com.example.a2in1app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row_guess_phrase.view.*


class RecyclerViewAdapter_GuessPhrase(private val guessText:ArrayList<String>): RecyclerView.Adapter<RecyclerViewAdapter_GuessPhrase.ItemViewHolder>(){
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row_guess_phrase,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val text=guessText[position]
        holder.itemView.apply {
            tvText.text=text
        }
    }

    override fun getItemCount()=guessText.size
}