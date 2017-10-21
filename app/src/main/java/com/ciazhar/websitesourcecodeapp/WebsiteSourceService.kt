package com.ciazhar.websitesourcecodeapp


import android.content.Context
import android.support.v4.content.AsyncTaskLoader
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by ciazhar on 10/21/17.
 *
 * [ Documentation Here]
 */

class WebsiteSourceService(context: Context?, var url : String) : AsyncTaskLoader<String>(context) {

    override fun onStartLoading() {
        forceLoad()
    }

    override fun loadInBackground(): String {
        var url = URL(url)
        var connection = url.openConnection() as HttpURLConnection
        var inputStream : InputStream

        connection.readTimeout = 10000
        connection.connectTimeout = 20000
        connection.requestMethod = "GET"
        connection.connect()

        inputStream = connection.inputStream

        var reader = BufferedReader(InputStreamReader(inputStream))
        var builder = StringBuilder()

        var line = reader.readLine()
        while (line != null) {
            builder.append(line + "\n")
            line = reader.readLine()
        }

        reader.close()
        inputStream.close()

        return builder.toString()
    }

}

