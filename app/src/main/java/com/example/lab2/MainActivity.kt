package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import db.AddDatabase
import db.Order
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import json.JsonData
import json.TestApiService

class MainActivity : AppCompatActivity() {
    private lateinit var db: AddDatabase
    lateinit var fromAddress: TextView
    lateinit var toAddress: TextView
    lateinit var carId: TextView
    lateinit var price: TextView
    lateinit var buttonGet: Button


    private fun initView(){
        fromAddress =  findViewById(R.id.fromAddress)
        toAddress =  findViewById(R.id.toAddress)
        carId =  findViewById(R.id.carId)
        price =  findViewById(R.id.price)
        buttonGet = findViewById(R.id.buttonGet)
        buttonGet.setOnClickListener {
            loadData()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDatabase()
        initView()
    }

    private fun loadData() {
        Log.d("API", "loadData")
        val service = TestApiService()
        service.getData(object : TestApiService.DataCallback {
            override fun onSuccess(data: JsonData) {
                displayData(data)
            }
            override fun onFailure() {
                displayError()
            }
        })
    }
    private fun displayData(data: JsonData) {
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
    private fun displayError() {
        Log.d("API", "error loading data")
        Toast.makeText(MainActivity@ this, R.string.error, Toast.LENGTH_LONG).show()
    }


    override fun onResume() {
        super.onResume()
        testDatabase()
    }
    private fun log(message: String) {
        Log.d("TEST_DB", message)
    }
    private fun testDatabase() {
        val order1 = Order(1,"lviv", "kiyv", "12:00", 12,25.0)
        val order2 = Order(2,"ternopil", "kiyv", "16:00", 8,45.0)
        val order3 = Order(3,"harkiv", "kiyv", "18:00", 2,27.0)
        val orders = mutableListOf<Order>()
        orders.add(order1)
        orders.add(order2)
        orders.add(order3)

        val dao = db.userDao()

        dao.insert(order1)
        log("insert $order1")
        dao.insert(order2)
        log("insert $order2")
        dao.insert(order3)
        log("insert $order3")

        dao.insert(orders)
        log("insert $orders")

        //UPDATE
        val modifyOrder = Order(2, "lviv", "harkiv","16:00", 3, 100.0)
        dao.update(modifyOrder)
        log("update $modifyOrder")

        //DELETE
        dao.delete(order3)
        log("delete $order3")

        //READ
        val readOrders = dao.getAll()
        for (order in readOrders) {
            log("read $order")
        }
    }

    private fun initDatabase(){
        db = Room.databaseBuilder(
            applicationContext,
            AddDatabase::class.java, "test-database"
        ).allowMainThreadQueries().build()
    }
}