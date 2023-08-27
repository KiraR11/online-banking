package com.example.mybank.bace
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mybank.data.Cards
import com.example.mybank.R
import com.example.mybank.data.MainDb

class AddCardActivity : AppCompatActivity(){
     var name_edit : EditText? = null
     var balance_edit : EditText? = null
     var errors_text :TextView?  = null
    var colorList: List<String> = listOf("red","green","blue")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_card)
        name_edit = findViewById(R.id.edit_name_card)
        balance_edit = findViewById(R.id.edit_balance_card)
        errors_text= findViewById(R.id.errors_text)
    }
    fun addMyCard(view: View){
        val ff = Intent(this.applicationContext,MainActivity::class.java)
        startActivity(ff)
    }
    fun creatNewCard(view: View){
        var id_card =(1000..9999).random()
        var color = colorList[(colorList.indices).random()]
        var name_card = name_edit?.text.toString()
        var balance_card: Double? = balance_edit?.text.toString().toDoubleOrNull()

        Log.d("MyLog","имя карты: ${name_card}")
        Log.d("MyLog","баланс карты: ${balance_card}")

        if(balance_card != null && name_card.length > 0) {
            val card = Cards(null,balance_card, id_card, name_card,color)
            val intent = Intent(applicationContext,MainActivity::class.java)
            val db = MainDb.getDb(this)
            Thread(Runnable {
                db.getDao().insertCard(card)//добавляем карту в базу данных
            }).start()
            startActivity(intent)//переход на список карт
        }
        else{ errors_text!!.text = "недопустимые значения" }
    }
}

