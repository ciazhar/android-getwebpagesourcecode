package com.ciazhar.websitesourcecodeapp

import android.R.layout.*
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.*
import android.widget.*
import android.widget.ArrayAdapter.createFromResource
import com.ciazhar.websitesourcecodeapp.R.array.protocol
import com.ciazhar.websitesourcecodeapp.R.id.*

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {

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

        if (supportLoaderManager.getLoader<Any>(0) != null) {
            supportLoaderManager.initLoader(0, null, this)
        }

    }

    fun getPageSource(view : View?){
        var protocolResult : String = spinner.selectedItem.toString()
        var urlResult :String = url.text.toString()
        var fullUrl : String

        var inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus.windowToken, HIDE_NOT_ALWAYS)

        if (checkConnection()){
            htmlResult.text=""
            fullUrl = protocolResult +urlResult
            var bundle = Bundle()
            bundle.putString("url",fullUrl)
            supportLoaderManager.restartLoader(0,bundle,this)
        }else{
            Toast.makeText(this, "Connection Failed",Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkConnection(): Boolean {
        var connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo!==null&&networkInfo.isConnected
    }

    override fun onLoadFinished(loader: Loader<String>?, data: String?) {
        htmlResult.text = data
    }

    override fun onCreateLoader(id: Int, args: Bundle): Loader<String> {
        return WebsiteSourceService(this, args.getString("url"))
    }

    override fun onLoaderReset(loader: Loader<String>?) {

    }

}
