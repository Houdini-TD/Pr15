package com.example.pract15

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(): BaseAdapter() {
    private val list = mutableListOf<String>()
    override fun getCount(): Int = list.count()
    override fun getItem(index: Int) = list[index]
    override fun getItemId(index: Int) = index.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(parent?.context)
        val view = convertView ?: inflater.inflate(R.layout.le_element, parent, false)
        val textView = view.findViewById<TextView>(R.id.tv)

        if (list[position].length > 50)
            textView.text = list[position].substring(0, 50)
        else
            textView.text = list[position]

        return view
    }

    fun addItem(text: String){
        list.add(text)
        notifyDataSetChanged()
    }

    fun setItem(id: Int, text: String){
        list[id] = text
        notifyDataSetChanged()
    }
}