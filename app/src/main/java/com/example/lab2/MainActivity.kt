package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import db.AddDatabase
import db.Order
import android.util.Log

class MainActivity : AppCompatActivity() {
    private lateinit var db: AddDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDatabase()
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