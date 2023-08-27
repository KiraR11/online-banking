package com.example.mybank.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Insert
    fun insertCard(card : Cards)

    @Query(value = "SELECT * FROM CARDS")
    fun getAllCard(): List<Cards>

    @Query("DELETE FROM CARDS WHERE id = :id")
    fun deleteById(id :Int)

    @Query("UPDATE CARDS SET balance = balance + :money WHERE id = :id")
    fun writeOn(id: Int, money:Double)

    @Query("UPDATE CARDS SET balance = balance - :money WHERE id = :id")
    fun writeOff(id: Int, money:Double)

    @Insert
    fun insertTransactions(transactions : Transactions)

    @Query(value = "SELECT * FROM TRANSACTIONS")
    fun getAllTransactions(): List<Transactions>

    @Query("UPDATE TRANSACTIONS SET Class = :Class WHERE idTran = :idTran")
    fun updateClass(Class :Float,idTran:Int)

    @Insert
    fun insertTransactData(transactData: TransactData)

    @Query(value = "SELECT COUNT(1) FROM transactData")
    fun existenceCheck(): Int

    @Query("SELECT * FROM transactData WHERE id = :id")
    fun getTransactDATA(id: Int) : TransactData

}

