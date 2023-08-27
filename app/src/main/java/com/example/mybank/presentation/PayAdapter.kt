package com.example.mybank.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybank.R
import com.example.mybank.bace.MainActivity
import com.example.mybank.bace.Pay
import com.example.mybank.databinding.ItemPayBinding

class PayAdapter(val listener: Pay): RecyclerView.Adapter<PayViewHolder>() {

    private val payList:List<String> = listOf(
        "Между своими",
        "На другой счет",
        "Мобильная связь",
        "Оплата ЖКХ"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayViewHolder {
        //val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.item_pay,parent,false)
        val itemBinding = ItemPayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PayViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PayViewHolder, position: Int) {
        holder.bind(payList[position],listener)
    }
    override fun getItemCount() = payList.size

    interface Listener{
        fun onClick(string: String)
    }
}