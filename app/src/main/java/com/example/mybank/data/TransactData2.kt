package com.example.mybank.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class TransactData2(
     var id: Int? = null,
     var classTran:Float? = null
    ):java.io.Serializable
