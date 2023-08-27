package com.example.mybank.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybank.R
import com.example.mybank.data.Transactions
import com.example.mybank.databinding.ItemCardBinding
import com.example.mybank.databinding.ItemTransactionBinding

class HistoryAdapter(private var transactionsList :List<Transactions>):
    RecyclerView.Adapter<HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemBinding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(itemBinding)

    }
    override fun getItemCount() = transactionsList.size
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(transactionsList[position])
    }
}