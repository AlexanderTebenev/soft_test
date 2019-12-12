package com.happyfriends.happyfriends.view.teporary_rooms_list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soft.R
import com.happyfriends.happyfriends.bean.KeyValueObject
import kotlinx.android.synthetic.main.item_key_value.view.*
import org.greenrobot.eventbus.EventBus
import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher


class JsonListAdapter(var items: List<KeyValueObject>, val context: Context): RecyclerView.Adapter<ViewHolderNews>() {

    private var recyclerView: RecyclerView? = null

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNews {
        return ViewHolderNews(LayoutInflater.from(context).inflate(R.layout.item_key_value ,parent, false),
            KeyEditTextListener(),ValueEditTextListener())

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }


    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolderNews, position: Int) {
        val room: KeyValueObject = items[position]
        holder.keyEditTextListener!!.updatePosition(holder.getAdapterPosition())
        holder.valueEditTextListener!!.updatePosition(holder.getAdapterPosition())
        holder.setData(items[position])
    }

    inner class KeyEditTextListener : TextWatcher {
        private var position: Int = 0

        fun updatePosition(position: Int) {
            this.position = position
        }

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
            // no op
        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
            items[position].key = charSequence.toString()
        }

        override fun afterTextChanged(editable: Editable) {
            // no op
        }
    }

   inner class ValueEditTextListener : TextWatcher {
        private var position: Int = 0

        fun updatePosition(position: Int) {
            this.position = position
        }

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
            // no op
        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
            items[position].value = charSequence.toString()
        }

        override fun afterTextChanged(editable: Editable) {
            // no op
        }
    }
}

class ViewHolderNews(view: View, keyEditTextListener: JsonListAdapter.KeyEditTextListener, valueEditTextListener: JsonListAdapter.ValueEditTextListener): RecyclerView.ViewHolder(view) {
    var keyEditTextListener: JsonListAdapter.KeyEditTextListener? = keyEditTextListener
    var valueEditTextListener: JsonListAdapter.ValueEditTextListener? = valueEditTextListener
    val key = view.key!!
    val value= view.value!!

    init {
        key.addTextChangedListener(keyEditTextListener)
        value.addTextChangedListener(valueEditTextListener)
    }

    lateinit var keyValueObject: KeyValueObject

    fun setData(keyValueObject: KeyValueObject) {
        this.keyValueObject = keyValueObject
        key.setText(keyValueObject.key)
        value.setText(keyValueObject.value)
    }
}

