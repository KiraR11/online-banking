package com.example.mybank.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.mybank.R
import com.example.mybank.bace.CardList
import com.example.mybank.data.Cards
import com.example.mybank.data.MainDb
import com.example.mybank.databinding.ItemCardBinding

class CardViewHolder(itemView: ItemCardBinding)
    : RecyclerView.ViewHolder(itemView.root) {
@SuppressLint("SetTextI18n")
    var textViewBalance = itemView.textViewBalance
    var textViewName = itemView.textViewName
    var textViewID = itemView.textViewID
    var imageView = itemView.imageView
fun bind(cards: Cards,listener: CardAdapter.Listener){

    with(itemView){cards.run {
        textViewName.text = name
        textViewBalance.text = balance.toString()
        textViewID.text = "·$number"
        //imageView выбираем инокну карты разного цвета из массива
        if(cards.color == "red")
            imageView.setImageResource(R.drawable.icon_credit_card_red)
        if(cards.color == "green")
            imageView.setImageResource(R.drawable.icon_credit_card_green)
        if(cards.color == "blue")
            imageView.setImageResource(R.drawable.icon_credit_card_blye)


        itemView.setOnClickListener{
            Log.d("MyLog","Удалить карту с именем ${cards.name}")
            listener.onClick(cards)
        }
    }
    }
}

}