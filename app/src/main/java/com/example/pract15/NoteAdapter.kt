package com.example.pract15

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast

class ListAdapter(): BaseAdapter() {
    val list = mutableListOf<String>()
    fun onListItemClick(element: Int){
        Toast.makeText(this, )
    }

    override fun getCount(): Int = list.count()
    override fun getItem(index: Int) = list[index]
    override fun getItemId(index: Int) = index.toLong()



    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }
}