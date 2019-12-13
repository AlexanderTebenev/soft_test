package remote.retrofit

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.ResponseBody


class Repository(private val qrCodeDataSource: QrCodeDataSource) {


    val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    var bitmap: MutableLiveData<Bitmap> = MutableLiveData()

    fun loadQrCode(json: String) : MutableLiveData<Bitmap> {
        uiScope.launch(viewModelJob) {

            var response = qrCodeDataSource.createQrCode("150x150", json)
            var bitmapResponse=BitmapFactory.decodeStream(response!!.await().byteStream())

            withContext(Main) {
                bitmap.value = bitmapResponse
            }
        }
        return bitmap
    }
}
