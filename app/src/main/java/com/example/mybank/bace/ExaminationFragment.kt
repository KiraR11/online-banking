package com.example.mybank.bace

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.mybank.data.MainDb
import com.example.mybank.data.Transactions
import com.example.mybank.databinding.FragmentExaminationBinding
import com.example.mybank.presentation.XAxisTimeFormatter
import com.example.mybank.presentation.YAxisTimeFormatter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("StaticFieldLeak")
private lateinit var binding:FragmentExaminationBinding

private lateinit var myData: DatabaseReference
private var USER_KEY :String = "Data_certain"
private lateinit var db : MainDb
private lateinit var chart: LineChart

class ExaminationFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExaminationBinding.inflate(inflater,container,false)
        myData = FirebaseDatabase.getInstance().getReference(USER_KEY)
        db = MainDb.getDb(requireActivity())
        chart = binding.charts
        initChar()//настройка графика



        updataChar(requireContext())//обновление графика

        return binding.root
    }

    //обновление графика
    private  fun updataChar(context: Context){
        val c = Calendar.getInstance()
        var tranAll = db.getDao().getAllTransactions()
        var tranToday : MutableList<Transactions> = mutableListOf()
        for(i in tranAll.indices)
            if(tranAll[i].data_day == c.get(Calendar.DAY_OF_MONTH))
                tranToday.add(tranAll[i])


        //var tranToday = db.getDao().getAllTransactions()
        var countTran = tranToday.size+2
        var arreyX = arrayOfNulls<Float>(countTran)
        var arreyY = arrayOfNulls<Float>(countTran)
        arreyX[0] = 0f
        arreyX[countTran-1] = 24f
        for(i in 1..countTran-2)
            arreyX[i] = tranToday[i-1].time_hour.toFloat() + tranToday[i-1].time_minute.toFloat()/60

        arreyY[0] = 0f
        arreyY[countTran-1] = 0f
        for(i in 1..countTran-2) {
            if (tranToday[i - 1].Class != null)
                arreyY[i] = tranToday[i - 1].Class
            else
                arreyY[i] = 0f//добавить надпись "обновите данные"
        }

        var entries: ArrayList<Entry> = ArrayList()
        for(i in arreyX.indices)
            entries.add(Entry(arreyX[i]!!,arreyY[i]!!))
        val dataset = LineDataSet(entries, "График первый")// На основании массива точек создадим первую линию с названием

        dataset.setDrawFilled(true)//рисование площеди под графиком
        val fillGradient = ContextCompat.getDrawable(context, com.example.mybank.R.drawable.green_red_grad)
        dataset.fillDrawable = fillGradient//градиент первые две цифры цвета означают прозрачность
        dataset.lineWidth = 0f//ширина линии
        //dataset.setDrawCircles(false)//отключение точек
        dataset.setCircleColor(com.example.mybank.R.color.blee)//цвет точек
        dataset.setDrawValues(false)//отключение значений
        dataset.color = com.example.mybank.R.color.white//цвет линии графика
        dataset.mode = LineDataSet.Mode.LINEAR//плавный график
        val data = LineData(dataset)
        chart.data = data

        chart.invalidate()//обновление графика
    }

    //настройка графика
    private fun initChar(){
        val leftYAxis: YAxis = chart.axisLeft//получение левой оси Y
        val rightAxis: YAxis = chart.axisRight//получение правой оси Y
        val xAxis: XAxis = chart.xAxis//получение  оси X

        chart.description.isEnabled = false//установка описания графика
        xAxis.setDrawGridLines(false)//хуй пойми что
        xAxis.position = XAxis.XAxisPosition.BOTTOM//установка оси Х вниз
        leftYAxis.isEnabled = false//отключение левой оси Y
        chart.legend.isEnabled = false//отключение легенд

        rightAxis.axisMinimum = 0f
        leftYAxis.axisMinimum = 0f
        xAxis.axisMaximum = 24f
        xAxis.axisMinimum = 0f
        xAxis.labelCount = 24

        xAxis.valueFormatter = XAxisTimeFormatter()//настройна значений оси х
        rightAxis.valueFormatter = YAxisTimeFormatter()//настройна значений оси y

        chart.animateY(700)//анимация графика
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ExaminationFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
