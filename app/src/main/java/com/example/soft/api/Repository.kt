package remote.retrofit

import android.content.Context
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Url
import android.content.ClipData.Item
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.single.SingleInternalHelper.toObservable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import java.io.File


class Repository(private val apiService: ApiService) {

     suspend fun createQrCode(size: String,data: String): ResponseBody {

        return apiService.createQrCode(size,data).await()
    }

}
