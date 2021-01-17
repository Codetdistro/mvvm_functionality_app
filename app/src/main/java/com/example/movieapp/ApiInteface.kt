package net.simplifiedcoding.data.network



import com.example.movieapp.ApiResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiInteface {

    @GET("listUsers")
    suspend fun getData() : Response<ApiResult>

    companion object{
        operator fun invoke(
        ) : ApiInteface {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://mvvmapi.herokuapp.com/")
                .build()
                .create(ApiInteface::class.java)
        }
    }
}
