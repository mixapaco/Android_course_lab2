package com.example.lab2.di

import com.example.lab2.data.IDataSource
import com.example.lab2.data.TestApiService
import com.example.lab2.data.api.RetrofitApiHelper
import com.example.lab2.ui.MainContract
import com.example.lab2.ui.MainPresenter


class DiHelper {

    companion object {

        private var mainPresenter: MainContract.Presenter? = null
        private var mainService: IDataSource? = null
        private var retrofitHelper: RetrofitApiHelper? = null

        fun getPresenter(view: MainContract.View): MainContract.Presenter {
//            if (mainPresenter == null) {
            mainPresenter = MainPresenter(view)
//            }

            return mainPresenter!!
        }

        //Singleton
        fun getService(): IDataSource {
            if (mainService == null) {
                mainService = TestApiService()
            }

            return mainService!!
        }

        //Singleton
        fun getRetrofitHelper(): RetrofitApiHelper {
            if (retrofitHelper == null) {
                retrofitHelper = RetrofitApiHelper()
                retrofitHelper?.init()
            }

            return retrofitHelper!!
        }
    }
}