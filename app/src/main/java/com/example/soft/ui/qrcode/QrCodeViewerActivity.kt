package com.example.soft.ui.qrcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import remote.retrofit.Repository
import androidx.databinding.DataBindingUtil
import com.example.soft.databinding.QrCodeActivityBinding
import android.graphics.Bitmap
import androidx.databinding.BindingAdapter
import android.widget.ImageView
import androidx.lifecycle.*
import com.example.soft.R
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class QrCodeViewerActivity : AppCompatActivity() {

    val qrCodeModel : QrCodeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        qrCodeModel.loadQrCode(intent.extras.getString("json"))
    }

    fun initBinding() {
        val binding: QrCodeActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.qr_code_activity)
        binding.viewmodel = qrCodeModel
        binding.lifecycleOwner = this
    }
}

@BindingAdapter("bind:imageBitmap")
fun loadImage(iv: ImageView, bitmap: MutableLiveData<Bitmap>) {
    iv.setImageBitmap(bitmap.value)
}


