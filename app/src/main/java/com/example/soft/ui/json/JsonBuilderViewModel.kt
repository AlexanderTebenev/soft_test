package com.example.soft.ui.json

import androidx.lifecycle.ViewModel
import org.json.JSONObject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soft.model.KeyValueObject


class JsonBuilderViewModel : ViewModel() {

    val items: LiveData<MutableList<KeyValueObject>>

    init {
        items = MutableLiveData<MutableList<KeyValueObject>>()
        items.value = mutableListOf()
    }

    fun createJson(): String {
        val json = JSONObject()
        items.value!!.forEach {
            json.put(it.key, it.value)
        }
        return json.toString()
    }

    fun addItem() {
        items.value!!.add(KeyValueObject())
    }
}