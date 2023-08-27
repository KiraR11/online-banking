package com.example.mybank.bace

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.mybank.R
import com.example.mybank.data.MainDb
import com.example.mybank.data.Transactions
import com.example.mybank.databinding.FragmentCardListBinding
import com.example.mybank.databinding.FragmentHistoryBinding
import com.example.mybank.presentation.CardAdapter
import com.example.mybank.presentation.HistoryAdapter

private lateinit var binding: FragmentHistoryBinding
private lateinit var historyAdapter: HistoryAdapter
private  var transactionsList: MutableList<Transactions> = mutableListOf()
private lateinit var db: MainDb

class HistoryFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate((inflater),container,false)
        val db = MainDb.getDb(requireActivity())
        transactionsList = db.getDao().getAllTransactions().toMutableList()
        binding.rvHistory.layoutManager = LinearLayoutManager(context)//
        binding.rvHistory.setHasFixedSize(true)
        historyAdapter = HistoryAdapter(transactionsList.reversed())
        binding.rvHistory.adapter = historyAdapter

        return binding.root
    }

    companion object {
        fun newInstance() =
            HistoryFragment().apply {}
            }
    }
