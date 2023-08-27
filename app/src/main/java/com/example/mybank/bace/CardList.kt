package com.example.mybank.bace

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybank.data.Cards
import com.example.mybank.data.MainDb
import com.example.mybank.databinding.FragmentCardListBinding
import com.example.mybank.presentation.CardAdapter




class CardList : Fragment(),CardAdapter.Listener{

    private lateinit var cardAdapter: CardAdapter
    var car : List<Cards> = mutableListOf()
    private  var alertDialog : AlertDialog? = null
    private lateinit var binding: FragmentCardListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardListBinding.inflate((inflater),container,false)
        val db = MainDb.getDb(requireActivity())
        initRV(db)
        binding.cardAdd.setOnClickListener{
            val start = Intent(context,AddCardActivity::class.java)
            startActivity(start)
        }
        return binding.root
    }

    //обработка нажатий на элементы RecyclerView
    override fun onClick(cards: Cards) {
        alertDialog = AlertDialog.Builder(requireContext()).
        setTitle("Удаление карты").
        setMessage("Вы хотите удалить карту?").
        setPositiveButton("ДА"){dialog,which ->
            val db = MainDb.getDb(requireContext())
            val id : Int? = cards.id
            id?.let { db.getDao().deleteById(it) }
            initRV(db)
         }.
         setNegativeButton("нет"){dialog,which -> }.show()
    }

    //обновление RecyclerView через базу данных
    fun initRV(db: MainDb){
        car = db.getDao().getAllCard()
        binding.myCardRv.layoutManager = LinearLayoutManager(context)//
        binding.myCardRv.setHasFixedSize(true)
        cardAdapter = CardAdapter(car.reversed(),this)
        binding.myCardRv.adapter = cardAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = CardList().apply { arguments = Bundle().apply {} }
    }
}