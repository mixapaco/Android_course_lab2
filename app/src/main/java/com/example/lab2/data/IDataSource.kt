package com.example.lab2.data

import com.example.lab2.data.model.JsonData

interface IDataSource {
    fun getData(callback: DataCallback)

    interface DataCallback {
        fun onSuccess(data: JsonData)
        fun onFailure()
    }
}