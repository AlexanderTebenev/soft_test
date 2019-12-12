package com.example.soft.ui.qrcode

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.happyfriends.happyfriends.bean.KeyValueObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.qr_code_activity.*
import okhttp3.ResponseBody
import remote.retrofit.Repository
import remote.retrofit.RepositoryProvider
import java.lang.Exception

class QrCodeViewModel(val repository: Repository) : ViewModel() {

   var bitmap: MutableLiveData<Bitmap> = MutableLiveData()
   suspend fun loadQrCode(json: String) {

       var response =  repository.createQrCode("150x150", json)
       bitmap.value = BitmapFactory.decodeStream(response!!.byteStream())

    }
}