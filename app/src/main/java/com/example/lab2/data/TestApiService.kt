package com.example.lab2.data

import com.example.lab2.data.api.RetrofitApiHelper
import com.example.lab2.data.api.TestApi
import com.example.lab2.data.model.JsonData
import com.example.lab2.di.DiHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestApiService : IDataSource {

    companion object {
        const val SECRET_KEY = "\$2b\$10\$.DI8X0edWswId/0Onz5ZROiSd.v83bvOyBY2w.zH/MIIwvgNERKKa"
    }

    var api: TestApi
    init {

        api = DiHelper.getRetrofitHelper().retrofit!!.create(TestApi::class.java)
    }
    override fun getData(callback: IDataSource.DataCallback) {
        api.getData(SECRET_KEY).enqueue(object : Callback<JsonData> {
            override fun onResponse(call: Call<JsonData>, response: Response<JsonData>) {
                if (response.code() == 200 && response.body() != null)
                    callback.onSuccess(response.body()!!)
                else
                    callback.onFailure()
            }
            override fun onFailure(call: Call<JsonData>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
}