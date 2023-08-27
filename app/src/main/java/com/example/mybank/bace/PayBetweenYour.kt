package com.example.mybank.bace

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.mybank.data.*
import com.example.mybank.databinding.ActivityBetweenYourBinding
import com.example.mybank.presentation.CardAdapterSpin
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

class PayBetweenYour: AppCompatActivity() {

    private lateinit var binding: ActivityBetweenYourBinding
    private lateinit var cardAdapter:CardAdapterSpin
    private  lateinit var cardList: MutableList<Cards>
    private lateinit var cardFrom: Cards
    private lateinit var cardTo: Cards
    private lateinit var db: MainDb
    private val SET_KEY: String = "Data_uncertain"//Data_uncertain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBetweenYourBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        var CardsError = true
        db = MainDb.getDb(this)

        cardList = db.getDao().getAllCard().toMutableList()
        if(cardList.size == 0){
            CardsError = false
            cardList.add(Cards(0,0.0,0,"",""))
        }
        cardAdapter = CardAdapterSpin(cardList,CardsError)
        binding.spinnerFromCard.adapter = cardAdapter
        binding.spinnerToCard.adapter = cardAdapter
        onClickErrorCards(binding.spinnerToCard)
        onClickErrorCards(binding.spinnerFromCard)
    }
    fun onClickErrorCards(spiner : Spinner){
        spiner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                cardFrom = cardList[binding.spinnerFromCard.selectedItem.toString().toInt()]
                cardTo = cardList[binding.spinnerToCard.selectedItem.toString().toInt()]
                if(cardFrom == cardTo && cardFrom.color != "")
                    binding.view1.visibility = View.VISIBLE
                else
                    binding.view1.visibility = View.INVISIBLE
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                return
            }
        })
    }

    fun onClickAll(view: View){
        binding.tbError.text = ""
    }
    fun onClickBack(view: View){
        val intent = Intent(applicationContext,MainActivity::class.java)
        startActivity(intent)
    }
    fun onClickTest(view: View){
        val intent = Intent(applicationContext,MainActivity::class.java)

        val c = Calendar.getInstance()
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        if(binding.rbMoh.isChecked){
            var number = (1..30).random()
            val transactions = Transactions(null,
                cardFrom.number.toString(),
                "Между своими счетами",
                10.0,month,day,hour,minute,null,number)
            //отправка в firebase
            onPuhFirebase(number)
            db.getDao().insertTransactions(transactions)
            startActivity(intent)
        }
        if(binding.rbNotMoh.isChecked){
            var number = (31..50).random()
            val transactions = Transactions(null,
                cardFrom.number.toString(),
                "Между своими счетами",
                10.0,month,day,hour,minute,null,number)
            db.getDao().insertTransactions(transactions)
            //отправка в firebase
            onPuhFirebase(number)
            startActivity(intent)
        }
    }
    fun onClickPay(view: View){
        cardFrom = cardList[binding.spinnerFromCard.getSelectedItem().toString().toInt()]
        cardTo = cardList[binding.spinnerToCard.getSelectedItem().toString().toInt()]
        val money: Double? = binding.tbMoneyTransfer.text.toString().toDoubleOrNull()
        if(cardFrom.color == "")
            binding.tbError.text = "<- добавте карту"
        else if(cardFrom == cardTo)
            binding.tbError.text = "выберите другую карту"
        else if(money == null) {
            binding.tbError.text = "вы не ввели сумму"

        }
        else if(cardFrom.balance < money)
            binding.tbError.text = "на карте недостаточно средств"
        else{
            //реализовать анимацию совершения транзакции
            cardFrom.id?.let { db.getDao().writeOff(it,money) }
            cardTo.id?.let { db.getDao().writeOn(it,money) }
            var number = (0..db.getDao().existenceCheck()).random()
            val c = Calendar.getInstance()
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)
            val transactions = Transactions(null,
                cardFrom.number.toString(),
                "Между своими счетами",
                money,month,day,hour,minute,null,number)
            //отправка в firebase
            db.getDao().insertTransactions(transactions)
            onPuhFirebase(number)
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun onPuhFirebase(number:Int){
        val myData = FirebaseDatabase.getInstance().getReference(SET_KEY)

        var tran : TransactData = db.getDao().getTransactDATA(number)
        var i = true
        myData.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val totalSize = dataSnapshot.childrenCount.toInt()
                while (i){
                    myData.child(totalSize.toString()).setValue(tran)
                    i = false
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException()
            }
        })

    }
}