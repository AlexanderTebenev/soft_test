package remote.retrofit

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.http.*
import retrofit2.http.GET

interface ApiService {

    @GET("create-qr-code/")
    fun createQrCode(@Query("size") size:String,  @Query("data") data:String ): Deferred<ResponseBody>

    companion object Factory {
        fun create(context: Context): ApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.qrserver.com/v1/")
                    .client(OkHttpClient().newBuilder().build())
                    .build()

            return retrofit.create(ApiService::class.java)
        }

    }
}