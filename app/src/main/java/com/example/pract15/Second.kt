package com.example.pract15

import android.app.Activity
import android.app.Notification.Action
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText

class Second : MyBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_act)

        val btnOk = findViewById<Button>(R.id.btnOk)
        val btnCancel = findViewById<Button>(R.id.btnCancel)
        btnOk.setOnClickListener {
            closeActivity(Activity.RESULT_OK, intent)
        }
        btnCancel.setOnClickListener {
            closeActivity(Activity.RESULT_CANCELED, intent)
        }

        val et = findViewById<EditText>(R.id.et)
        et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val isTextNotEmpty = !s.isNullOrBlank()
                btnOk.isEnabled = isTextNotEmpty
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        val text = intent.getStringExtra(EXTRA_TEXT) ?: ""
        val editText = findViewById<EditText>(R.id.et)
        editText.setText(text)
    }

    fun closeActivity(resultCode: Int, intent: Intent){
        val et = findViewById<EditText>(R.id.et)
        val intent = Intent()
        intent.putExtra(EXTRA_ACTION_CODE, intent.getIntExtra(EXTRA_ACTION_CODE, CREATE_ACTION))
        intent.putExtra(EXTRA_TEXT, et.text)
        intent.putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, 0))

        setResult(resultCode, intent)
        finish()
    }
}