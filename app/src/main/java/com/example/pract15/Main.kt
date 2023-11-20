package com.example.pract15

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts

class Main : MyBaseActivity() {
        private lateinit var adapter: ListAdapter
        private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_act)
        btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            callSecondForm(-1, CREATE_ACTION)
        }

        adapter = ListAdapter()
        val listView = findViewById<ListView>(R.id.lv)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            callSecondForm(position, EDIT_ACTION)
        }
    }

    fun callSecondForm(position: Int, action: Int) {
        val intent = Intent(this, Second::class.java)
        if (action == EDIT_ACTION) {
                intent.putExtra(EXTRA_TEXT, adapter.getItem(position))
                intent.putExtra(EXTRA_ID, position)
            }
        intent.putExtra(EXTRA_ACTION_CODE, action)
        secondActResultLauncher.launch(intent)
    }

    private val secondActResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    val id: Int = data.getIntExtra(EXTRA_ID, 1)
                    val text: String = data.getStringExtra(EXTRA_TEXT) ?: ""
                    println("EXTRA_TEXT: $text")
                    val actionCode = data.getIntExtra(EXTRA_ACTION_CODE, CREATE_ACTION)
                    when (actionCode){
                        CREATE_ACTION -> adapter.addItem(text)
                        EDIT_ACTION -> adapter.setItem(id, text)
                    }
                }
            }
        }

}