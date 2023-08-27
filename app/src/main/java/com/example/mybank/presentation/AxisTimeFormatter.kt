package com.example.mybank.presentation

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class XAxisTimeFormatter: ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        when(value){
            2f ->  return "2:00"
            6f ->  return "6:00"
            10f ->  return "10:00"
            14f ->  return "14:00"
            18f ->  return "18:00"
            22f ->  return "22:00"
        }
        return ""
    }
}

class YAxisTimeFormatter: ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        when(value){
            0.0f -> return  "0%"
            0.2f ->  return "20%"
            0.4f ->  return "40%"
            0.6f ->  return "60%"
            0.8f ->  return "80%"
            1.0f -> return  "100%"
        }
        return ""
    }
}