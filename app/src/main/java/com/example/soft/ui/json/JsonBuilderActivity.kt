package com.example.soft.ui.json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import com.happyfriends.happyfriends.bean.KeyValueObject
import com.happyfriends.happyfriends.view.teporary_rooms_list.JsonListAdapter
import androidx.lifecycle.ViewModelProviders
import com.example.soft.R
import com.example.soft.ui.qrcode.QrCodeViewerActivity


class JsonBuilderActivity : AppCompatActivity() {

    lateinit var adapter: JsonListAdapter
    private val builderModel by lazy {ViewModelProviders.of(this).get(JsonBuilderViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        initButtons()

        builderModel.items.observe(this, Observer {
                adapter!!.items=it
                adapter!!.notifyDataSetChanged()
        })
    }

    fun initAdapter() {
        adapter = JsonListAdapter(builderModel.items.value as List<KeyValueObject>, this)
        var layoutmanager = LinearLayoutManager(this)
        json_recycler_view.layoutManager = layoutmanager
        json_recycler_view.adapter = adapter
    }

    fun initButtons() {
        show_qr_code.setOnClickListener {
            val intent = Intent(this, QrCodeViewerActivity::class.java)
            intent.putExtra("json", builderModel.createJson())
            startActivity(intent)
        }

        add_item.setOnClickListener {
            builderModel!!.addItem()
            adapter!!.notifyItemInserted(builderModel.items.value!!.size)
        }
    }
}
