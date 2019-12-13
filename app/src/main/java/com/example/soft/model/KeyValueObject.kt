package com.example.soft.model

class KeyValueObject  {

    var key: String? = null
    var value: String? = null

    fun onKeyTextChanged(s: CharSequence){
        key=s.toString()
    }


    fun onValueTextChanged(s: CharSequence){
        value=s.toString()
    }
}