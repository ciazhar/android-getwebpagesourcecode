package com.ciazhar.websitesourcecodeapp

import android.R.layout.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ArrayAdapter.createFromResource
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.ciazhar.websitesourcecodeapp.R.array.protocol
import com.ciazhar.websitesourcecodeapp.R.id.*

class MainActivity : AppCompatActivity() {

    private lateinit var url : EditText
    private lateinit var spinner : Spinner
    private lateinit var spinnerItem : ArrayAdapter<CharSequence>
    private lateinit var htmlResult : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        url = findViewById(main_get_url) as EditText
        spinner = findViewById(main_spinner_protocol) as Spinner
        spinnerItem = createFromResource(this, protocol, simple_spinner_item)
        spinnerItem.setDropDownViewResource(simple_spinner_dropdown_item)
        spinner.adapter = spinnerItem
        htmlResult = findViewById(main_text_get_html) as TextView

    }
}
