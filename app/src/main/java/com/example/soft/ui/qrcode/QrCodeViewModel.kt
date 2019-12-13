package com.example.soft.ui.qrcode

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import remote.retrofit.Repository

class QrCodeViewModel(val repository: Repository) : ViewModel() {

    var bitmap: MutableLiveData<Bitmap> = MutableLiveData()


    fun loadQrCode(json:String){
        bitmap = repository.loadQrCode(json)
    }


    override fun onCleared() {
        super.onCleared()
        repository.viewModelJob.cancel()
    }
}