package com.example.mybank.presentation

import android.annotation.SuppressLint
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import com.example.mybank.R
import com.example.mybank.data.Cards
import com.example.mybank.databinding.ItemCardBinding
import com.example.mybank.databinding.ItemCardSpinBinding

class CardAdapterSpin(private  var cardList: List<Cards>,private var error:Boolean): SpinnerAdapter {

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        //this.registerDataSetObserver(observer)//
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        //this.unregisterDataSetObserver(observer)//
    }

    override fun getCount() = cardList.size

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()//
    }

    override fun hasStableIds(): Boolean {
        return true//
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemBinding = ItemCardSpinBinding.inflate(LayoutInflater.from(parent!!.context), parent, false)
        Create(itemBinding,position)
        return itemBinding.root
    }

    override fun getItemViewType(position: Int): Int {
       return 0
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun isEmpty(): Boolean {
        return  this.isEmpty//
    }

    @SuppressLint("SetTextI18n")
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //val itemView:View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_card_spin,parent,false)
        val itemBinding = ItemCardSpinBinding.inflate(LayoutInflater.from(parent!!.context), parent, false)
        Create(itemBinding,position)
        return itemBinding.root
    }
    fun Create(itemView:ItemCardSpinBinding,position:Int){

        if(error) {
            cardList.run {
                itemView.nameCard.text = cardList[position].name
                itemView.balanseCard.text = cardList[position].balance.toString()
                itemView.idCard.text = "·${cardList[position].number}"
                //imageView выбираем инокну карты разного цвета из массива
                if (cardList[position].color == "red")
                    itemView.ivCard.setImageResource(R.drawable.icon_credit_card_red)
                if (cardList[position].color == "green")
                    itemView.ivCard.setImageResource(R.drawable.icon_credit_card_green)
                if (cardList[position].color == "blue")
                    itemView.ivCard.setImageResource(R.drawable.icon_credit_card_blye)
                }
            }
        else{
            cardList.run {
                itemView.nameCard.text = "добавте карту"
                itemView.balanseCard.visibility = View.INVISIBLE
                itemView.idCard.visibility = View.INVISIBLE
                itemView.ivCard.visibility = View.INVISIBLE
            }
        }
    }
}
