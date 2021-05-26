package com.example.lab2.ui

import com.example.lab2.data.model.JsonData


interface MainContract {
    interface View {
        fun displayData(data: JsonData)
        fun displayError()

    }

    interface Presenter {
        fun loadData()
    }
}