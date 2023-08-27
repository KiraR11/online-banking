package com.example.mybank.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class Cards(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "balance")
    var balance: Double,
    @ColumnInfo(name = "number")
    var number: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "color")
    var color: String,
    ):java.io.Serializable
