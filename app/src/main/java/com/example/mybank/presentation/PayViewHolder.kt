package com.example.mybank.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mybank.R
import com.example.mybank.data.Cards
import com.example.mybank.databinding.ItemPayBinding


class PayViewHolder( itemView: ItemPayBinding)
    : RecyclerView.ViewHolder(itemView.root) {
    @SuppressLint("SetTextI18n")
    var image_pay = itemView.imagePay
    var textPay = itemView.textPay
    fun bind(string: String, listener: PayAdapter.Listener){
        with(itemView){string.run {
            textPay.text = string
            when(string){
                "Между своими счетами"-> image_pay.setImageResource(R.drawable.my_card_pay)
                "Оплата ЖКХ"-> image_pay.setImageResource(R.drawable.jkh_pay)
                "Мобильная связь"-> image_pay.setImageResource(R.drawable.telefon_pay)
                "На другой счет"-> image_pay.setImageResource(R.drawable.other_card_pay)
            }
            itemView.setOnClickListener{
                listener.onClick(string)
            }
        }
        }

    }

}