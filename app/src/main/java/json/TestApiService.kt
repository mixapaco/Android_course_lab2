package json

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestApiService {

    companion object {
        const val SECRET_KEY = "\$2b\$10\$.DI8X0edWswId/0Onz5ZROiSd.v83bvOyBY2w.zH/MIIwvgNERKKa"
    }

    var api: TestApi
    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(TestApi::class.java)
    }
    fun getData(callback: DataCallback) {
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
    interface DataCallback {
        fun onSuccess(data: JsonData)
        fun onFailure()
    }


}