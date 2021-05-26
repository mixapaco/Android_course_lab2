package com.example.lab2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lab2.R
import com.example.lab2.data.model.JsonData
import com.example.lab2.di.DiHelper

class MainActivity : AppCompatActivity(), MainContract.View {
    lateinit var fromAddress: TextView
    lateinit var toAddress: TextView
    lateinit var carId: TextView
    lateinit var price: TextView
    lateinit var buttonGet: Button

    var presenter: MainContract.Presenter = DiHelper.getPresenter(this)

    private fun initView(){
        fromAddress =  findViewById(R.id.fromAddress)
        toAddress =  findViewById(R.id.toAddress)
        carId =  findViewById(R.id.carId)
        price =  findViewById(R.id.price)
        buttonGet = findViewById(R.id.buttonGet)
        buttonGet.setOnClickListener {
            presenter.loadData()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    override fun displayData(data: JsonData) {
        Log.d("API", "${data.getToAddress()}")
        Log.d("API", "${data.getFromAddress()}")
        Log.d("API", "${data.getCarId()}")
        Log.d("API", "${data.getTime()}")
        Log.d("API", "${data.getPrice()}")
        fromAddress.setText("${data.getFromAddress()}")
        toAddress.setText("${data.getToAddress()}")
        carId.setText("Машина ${data.getCarId()} і час: ${data.getTime()}")
        price.setText("Ціна: ${data.getPrice()}")
    }
    override fun displayError() {
        Log.d("API", "error loading data")
        Toast.makeText(MainActivity@ this, R.string.error, Toast.LENGTH_LONG).show()
    }


    override fun onResume() {
        super.onResume()
    }

}