package com.example.soft.di

import android.content.Context
import com.example.soft.ui.json.JsonBuilderViewModel
import com.example.soft.ui.qrcode.QrCodeViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.reactivex.schedulers.Schedulers.single
import okhttp3.OkHttpClient
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.get
import org.koin.dsl.module
import org.koin.experimental.builder.create
import remote.retrofit.QrCodeDataSource
import remote.retrofit.Repository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val myModule =  module{

    viewModel { QrCodeViewModel(get()) }
    viewModel { JsonBuilderViewModel() }

    single {
        createDataSource(androidContext())
    }

    single {
        createRepository(get())
    }
}

fun createDataSource(context: Context): QrCodeDataSource {
    val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.qrserver.com/v1/")
        .client(OkHttpClient().newBuilder().build())
        .build()

    return retrofit.create(QrCodeDataSource::class.java)
}

fun createRepository(qrCodeDataSource: QrCodeDataSource): Repository {
    return Repository(qrCodeDataSource)
}
