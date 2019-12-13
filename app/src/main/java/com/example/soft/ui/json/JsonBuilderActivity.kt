package com.example.soft.ui.json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soft.model.KeyValueObject
import androidx.lifecycle.ViewModelProviders
import com.example.soft.R
import com.example.soft.adapter.JsonListAdapter
import com.example.soft.ui.qrcode.QrCodeViewerActivity
import org.koin.android.viewmodel.ext.android.viewModel


class JsonBuilderActivity : AppCompatActivity() {

    lateinit var adapter: JsonListAdapter
    val builderModel : JsonBuilderViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initButtons()
        initAdapter()
    }

    fun initAdapter() {
        adapter = JsonListAdapter(builderModel.items.value as List<KeyValueObject>)
        var layoutmanager = LinearLayoutManager(this)
        json_recycler_view.layoutManager = layoutmanager
        json_recycler_view.adapter = adapter
    }

    fun initButtons() {
        add_item.setOnClickListener {addItem()}
        show_qr_code.setOnClickListener { showQrCode() }
    }

    fun showQrCode() {
        val intent = Intent(this, QrCodeViewerActivity::class.java)
        intent.putExtra("json", builderModel.createJson())
        startActivity(intent)
    }

    fun addItem() {
        builderModel!!.addItem()
        adapter!!.notifyItemInserted(builderModel.items.value!!.size)
    }
}
