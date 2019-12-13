package com.example.soft.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soft.R
import androidx.databinding.DataBindingUtil
import com.example.soft.databinding.ItemKeyValueBinding
import com.example.soft.model.KeyValueObject
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter


class JsonListAdapter(var items: List<KeyValueObject>): RecyclerView.Adapter<ViewHolderNews>() {

    private var recyclerView: RecyclerView? = null
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNews {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ItemKeyValueBinding= DataBindingUtil.inflate(inflater, R.layout.item_key_value, parent, false)
        return ViewHolderNews(binding)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onBindViewHolder(holder: ViewHolderNews, position: Int) {
        holder.bind(items[position])
    }
}

class ViewHolderNews(val itemKeyValueBinding:  ItemKeyValueBinding): RecyclerView.ViewHolder(itemKeyValueBinding.root) {
    fun bind(item: KeyValueObject) {
        with(itemKeyValueBinding) {
            itemKeyValueBinding.keyValueObject=item
        }
    }
}

