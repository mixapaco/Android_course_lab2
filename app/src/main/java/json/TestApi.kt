package json

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TestApi {
    @GET("b/609acbed6e36c66e53611b75/2")
    fun getData(@Header("secret-key") secretKey: String): Call<JsonData>

}