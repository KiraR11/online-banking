package com.example.mybank.bace

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybank.R
import com.example.mybank.data.*
import com.example.mybank.presentation.CardAdapter
import com.example.mybank.databinding.ActivityMainBinding
import com.example.mybank.presentation.PayAdapter
import com.google.firebase.database.*
import com.google.gson.GsonBuilder
import java.io.InputStream


class MainActivity : AppCompatActivity(){

    lateinit var bild_view : ActivityMainBinding
    private lateinit var db: MainDb

    private lateinit var myData: DatabaseReference
    private var USER_KEY :String = "Data_certain"
    private var Tran: List<TransactData2>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bild_view = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(bild_view.root)
        db = MainDb.getDb(this)
        val check = db.getDao().existenceCheck()
        if(check <= 0){
        Thread {
            val transactList = ConvJSON("data3.json")
            for (tran in transactList)
                db.getDao().insertTransactData(tran)
            //создание стартовых карт
           testStart()
        }.start() }

        myData = FirebaseDatabase.getInstance().getReference(USER_KEY)

        //получение данных из бд

        myData.addValueEventListener(object : ValueEventListener {
            override fun onDataChange( dataSnapshot: DataSnapshot){
                var sizeDb = dataSnapshot.childrenCount.toInt()
                if(sizeDb > 0) {
                    Tran = ConvJSON2(dataSnapshot.value.toString())
                    var f = true
                    for(chil in Tran!!) {
                        if(chil.classTran!! >= 0.6f  && f) {
                            Toast.makeText(
                                this@MainActivity,
                                "Риск мошеннеческой транзакции!",
                                Toast.LENGTH_SHORT
                            ).show()//уведомление
                            f = false
                        }
                        db.getDao().updateClass(chil.classTran!!, chil.id!!)
                    }
                    myData.removeValue()
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        supportFragmentManager.beginTransaction().
        replace(R.id.pay_frog,CardList.newInstance()).commit()

        bild_view.btMenu.selectedItemId = R.id.item_cards
        bild_view.btMenu.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.item_cards->{supportFragmentManager.beginTransaction().
                replace(R.id.pay_frog,CardList.newInstance()).commit()
                }
                R.id.item_pay->{supportFragmentManager.beginTransaction().
                replace(R.id.pay_frog,Pay.newInstance()).commit()
                }
                R.id.item_history->{supportFragmentManager.beginTransaction().
                replace(R.id.pay_frog,HistoryFragment.newInstance()).commit()
                }
                R.id.item_test->{supportFragmentManager.beginTransaction().
                replace(R.id.pay_frog,ExaminationFragment.newInstance()).commit()}
            }
            true
        }
    }
    fun ConvJSON(fileName : String):List<TransactData> {
        val gson = GsonBuilder().create()
        val readerStream: InputStream = assets.open(fileName)
        var readerString = readerStream.bufferedReader().use { it.readText() }
        val data = gson.fromJson(readerString, Array<TransactData>::class.java).toList()
        return data
    }
    fun testStart(){
        val card1 = Cards(null,1000.0,1234,"Карта №1","blue")
        db.getDao().insertCard(card1)
        val card2 = Cards(null,2000.0,1235,"Карта №2","red")
        db.getDao().insertCard(card2)


        val tran1 = Transactions(null,"1234","Между своими счетами",200.0,3,3,2,30,0.12f,null)
        db.getDao().insertTransactions(tran1)
        val tran2 = Transactions(null,"1234","На мобильный счет",500.0,3,3,6,12,0.2f,null)
        db.getDao().insertTransactions(tran2)
        val tran3 = Transactions(null,"1234","На другой счет",1000.0,3,3,8,43,0.5f,null)
        db.getDao().insertTransactions(tran3)
        val tran4 = Transactions(null,"1234","На другой счет",15000.0,3,3,12,40,0.77f,null)
        db.getDao().insertTransactions(tran4)
        val tran5 = Transactions(null,"1234","На другой счет",20000.0,3,3,13,10,0.87f,null)
        db.getDao().insertTransactions(tran5)
        val tran8 = Transactions(null,"1234","На другой счет",30000.0,3,3,14,30,0.94f,null)
        db.getDao().insertTransactions(tran8)
        val tran6 = Transactions(null,"1234","Между своими счетами",1000.0,3,3,18,30,0.3f,null)
        db.getDao().insertTransactions(tran6)
        val tran7 = Transactions(null,"1234","На мобильный счет",50.0,3,3,20,30,0.13f,null)
        db.getDao().insertTransactions(tran7)


    }
    //конвертируем JSON в TransactData2
    fun ConvJSON2(strTran : String):List<TransactData2> {
        val gson = GsonBuilder().create()
        val data = gson.fromJson(strTran, Array<TransactData2>::class.java).toList()
        return data
    }
}


