package com.example.lab2.ui

import android.util.Log
import com.example.lab2.data.IDataSource
import com.example.lab2.data.model.JsonData
import com.example.lab2.data.TestApiService
import com.example.lab2.di.DiHelper

/**
 * Business Logic
 */
class MainPresenter(val view: MainContract.View) : MainContract.Presenter {

    val service: IDataSource = DiHelper.getService()

    override  fun loadData() {
        Log.d("API", "loadData")
        service.getData(object : IDataSource.DataCallback {
            override fun onSuccess(data: JsonData) {
                view.displayData(data)
            }
            override fun onFailure() {
                view.displayError()
            }
        })
    }
}