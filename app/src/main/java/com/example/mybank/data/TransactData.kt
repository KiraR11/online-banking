package com.example.mybank.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "transactData")
data class TransactData(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: Int? = null,

    @ColumnInfo(name = "amount")
    @SerializedName("Amount") val amount: String? = null,

    @ColumnInfo(name = "class")
    @SerializedName("Class") var classTran:String? = null,

    @ColumnInfo(name = "time")
    @SerializedName("Time") val time: String? = null,

    @ColumnInfo(name = "V1")
    @SerializedName("V1") val v1: String? = null,

    @ColumnInfo(name = "V10")
    @SerializedName("V10")val v10: String? = null,

    @ColumnInfo(name = "V11")
    @SerializedName("V11") val v11: String? = null,

    @ColumnInfo(name = "V12")
    @SerializedName("V12")val v12: String? = null,

    @ColumnInfo(name = "V13")
    @SerializedName("V13") val v13: String? = null,

    @ColumnInfo(name = "V14")
    @SerializedName("V14") val v14: String? = null,

    @ColumnInfo(name = "V15")
    @SerializedName("V15") val v15: String? = null,

    @ColumnInfo(name = "V16")
    @SerializedName("V16") val v16: String? = null,

    @ColumnInfo(name = "V17")
    @SerializedName("V17") val v17: String? = null,

    @ColumnInfo(name = "V18")
    @SerializedName("V18") val v18: String? = null,

    @ColumnInfo(name = "V19")
    @SerializedName("V19") val v19: String? = null,

    @ColumnInfo(name = "V2")
    @SerializedName("V2") val v2: String? = null,

    @ColumnInfo(name = "V20")
    @SerializedName("V20") val v20: String? = null,

    @ColumnInfo(name = "V21")
    @SerializedName("V21") val v21: String? = null,

    @ColumnInfo(name = "V22")
    @SerializedName("V22") val v22: String? = null,

    @ColumnInfo(name = "V23")
    @SerializedName("V23") val v23: String? = null,

    @ColumnInfo(name = "V24")
    @SerializedName("V24") val v24: String? = null,

    @ColumnInfo(name = "V25")
    @SerializedName("V25") val v25: String? = null,

    @ColumnInfo(name = "V26")
    @SerializedName("V26") val v26: String? = null,

    @ColumnInfo(name = "V27")
    @SerializedName("V27") val v27: String? = null,

    @ColumnInfo(name = "V28")
    @SerializedName("V28") val v28: String? = null,

    @ColumnInfo(name = "V3")
    @SerializedName("V3")val v3: String? = null,

    @ColumnInfo(name = "V4")
    @SerializedName("V4")val v4: String? = null,

    @ColumnInfo(name = "V5")
    @SerializedName("V5")val v5: String? = null,

    @ColumnInfo(name = "V6")
    @SerializedName("V6")val v6: String? = null,

    @ColumnInfo(name = "V7")
    @SerializedName("V7")val v7: String? = null,

    @ColumnInfo(name = "V8")
    @SerializedName("V8")val v8: String? = null,

    @ColumnInfo(name = "V9")
    @SerializedName("V9")val v9: String? = null,

):java.io.Serializable