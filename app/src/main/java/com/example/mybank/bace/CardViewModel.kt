package com.example.mybank.bace

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mybank.data.Cards

class CardViewModel: ViewModel() {
    val userCardList : MutableLiveData<List<Cards>> = MutableLiveData()

    fun updateUsersCard(cards: List<Cards>)
    {
        userCardList.value = cards
    }
    fun getdateUsersCard() = userCardList

}
