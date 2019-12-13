package remote.retrofit

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.http.*
import retrofit2.http.GET

interface QrCodeDataSource {

    @GET("create-qr-code/")
    fun createQrCode(@Query("size") size:String,  @Query("data") data:String ): Deferred<ResponseBody>

}