package com.example.mybank.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Cards::class,Transactions::class,TransactData::class], version = 1)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {
        private var db: MainDb? = null

        fun getDb(context: Context): MainDb {
            return if (db == null) {
                db = Room.databaseBuilder(
                    context.applicationContext,
                    MainDb::class.java,
                    "data_base.db"
                ).allowMainThreadQueries().build()
                db as MainDb
            }
            else {
                db as MainDb
            }
        }
    }
}

