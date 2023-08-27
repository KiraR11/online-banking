package com.example.mybank.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybank.data.Cards
import com.example.mybank.R
import com.example.mybank.databinding.ItemCardBinding
import com.example.mybank.databinding.ItemPayBinding


class CardAdapter(private var cardList: List<Cards>,val listener :Listener)
    : RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        //val itemView :View = LayoutInflater.from(parent.context).inflate(R.layout.item_card,parent,false)
        val itemBinding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(itemBinding)

    }
    override fun getItemCount() = cardList.size
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(cardList[position],listener)
    }
    interface Listener{
        fun onClick(cards: Cards)
    }

}