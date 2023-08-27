package com.example.mybank.presentation

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mybank.R
import com.example.mybank.data.Cards
import com.example.mybank.data.Transactions
import com.example.mybank.databinding.ItemTransactionBinding


class HistoryViewHolder(itemView : ItemTransactionBinding): RecyclerView.ViewHolder(itemView.root) {
    var tv_tran_from = itemView.tvTranFrom
    var tv_tran_money = itemView.tvTranMoney
    var tv_tran_time = itemView.tvTranTime
    var tv_tran_to = itemView.tvTranTo
    var iv_tran = itemView.ivTran
    var iv_risk = itemView.ivRisk
    var tv_risk = itemView.tvRisk
    fun bind(transactions: Transactions) {

        with(itemView) {
            transactions.run {
                var time: String =
                    transactions.time_hour.toString() + ":" + transactions.time_minute
                var mount = ""
                mount = if (transactions.data_mount > 8)
                    (transactions.data_mount + 1).toString()
                else
                    "0" + (transactions.data_mount + 1).toString()
                var day = if(transactions.data_day>9)
                    (transactions.data_day).toString()
                else "0" + (transactions.data_day).toString()
                var data: String = mount + "." + day
                tv_tran_to.text =  transactions.where
                tv_tran_from.text = "c карты ·" + transactions.cardFrom
                tv_tran_money.text = transactions.money.toString() + "₽"
                tv_tran_time.text = data + " " + time
                Log.d("MyLog", "${transactions.Class.toString()}")
                when (transactions.where) {
                    "Между своими счетами" -> iv_tran.setImageResource(R.drawable.my_card_tran)
                    "На мобильный счет" -> iv_tran.setImageResource(R.drawable.phon_tran)
                    "На другой счет" -> iv_tran.setImageResource(R.drawable.other_card_tran)
                    //дабавить другие платижи
                }
                if(transactions.Class == null) {
                    iv_risk.setImageResource(R.drawable.risk_answer)
                    tv_risk.text = ""
                }
                else if (transactions.Class!! < 0.15f) {
                    iv_risk.setImageResource(R.drawable.dots_15)
                    tv_risk.text = (transactions.Class!! * 100).toInt().toString() + "%"
                }
                else if (transactions.Class!! < 0.35f){
                    iv_risk.setImageResource(R.drawable.dots_35)
                    tv_risk.text = (transactions.Class!! * 100).toInt().toString() + "%"
                }
                else if (transactions.Class!! < 0.5f ) {
                    iv_risk.setImageResource(R.drawable.dots_50)
                    tv_risk.text = (transactions.Class!! * 100).toInt().toString() + "%"
                }
                else if (transactions.Class!! < 0.75f) {
                    iv_risk.setImageResource(R.drawable.dots_75)
                    tv_risk.text = (transactions.Class!! * 100).toInt().toString() + "%"
                }
                else {
                    iv_risk.setImageResource(R.drawable.dots_90)
                    tv_risk.text = (transactions.Class!! * 100).toInt().toString() + "%"
                }
            }


        }

    }
}