package com.example.mybank.bace

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybank.R
import com.example.mybank.data.MainDb
import com.example.mybank.databinding.FragmentPayBinding
import com.example.mybank.presentation.PayAdapter


class Pay : Fragment(),PayAdapter.Listener{

    private lateinit var payAdapter: PayAdapter
    private lateinit var binding:FragmentPayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPayBinding.inflate((inflater),container,false)
        //
        binding.payRV.layoutManager = LinearLayoutManager(context)//
        binding.payRV.setHasFixedSize(true)
        payAdapter = PayAdapter(this)
        binding.payRV.adapter = payAdapter

        return binding.root
    }

    override fun onClick(string: String) {
        //реализовать переход на лайауты платежей
        when(string){
            "Между своими"-> {
                val intent = Intent(context,PayBetweenYour::class.java)
                startActivity(intent)
            }
            "Оплата ЖКХ"-> {}
            "Мобильная связь"-> {}
            "На другой счет"-> {}
        }
    }



    companion object {
        @JvmStatic
        fun newInstance() = Pay().apply { arguments = Bundle().apply {} }
    }


}