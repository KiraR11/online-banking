package com.example.mybank.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transactions(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "cardFrom")
    var cardFrom: String,
    @ColumnInfo(name = "where")
    var where: String,
    @ColumnInfo(name = "money")
    var money: Double,
    @ColumnInfo(name = "data_mount")
    var data_mount: Int,
    @ColumnInfo(name = "data_day")
    var data_day: Int,
    @ColumnInfo(name = "time_hour")
    var time_hour: Int,
    @ColumnInfo(name = "time_minute")
    var time_minute: Int,
    @ColumnInfo(name = "class")
    var Class: Float? = null,
    @ColumnInfo(name = "idTran")
    var idTran: Int? = null

):java.io.Serializable
