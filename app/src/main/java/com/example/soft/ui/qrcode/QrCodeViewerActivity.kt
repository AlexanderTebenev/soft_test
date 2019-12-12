package com.example.soft.ui.qrcode

import android.app.Application
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import remote.retrofit.Repository
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.databinding.DataBindingUtil
import com.example.soft.databinding.QrCodeActivityBinding
import kotlinx.android.synthetic.main.qr_code_activity.*
import remote.retrofit.RepositoryProvider
import android.graphics.Bitmap
import androidx.databinding.BindingAdapter
import android.widget.ImageView
import androidx.lifecycle.*
import com.example.soft.R
import kotlinx.coroutines.*


class QrCodeViewerActivity : AppCompatActivity() {
    private val viewModelScope = CoroutineScope(Dispatchers.Main )
    private val qrCodeModel by lazy {
        ViewModelProviders.of(this, MyViewModelFactory(RepositoryProvider.getRepository(this)))
            .get(QrCodeViewModel::class.java!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        viewModelScope.async {
            loadQRcode()
        }
    }

    suspend fun loadQRcode() {
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

class MyViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QrCodeViewModel(repository) as T
    }
}
