package com.example.amonict2.DAO

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class CallServiceJD {


    companion object {
        var ruta = "http://10.0.2.2:5000"

        enum class method {
            GET,
            POST,
            DELETE,
            PUT
        }

        fun StartQuery(address: String, method: method, data: String, servicesJD: servicesJD) {

            CoroutineScope(Dispatchers.IO).launch {
                var client = URL("$ruta/$address").openConnection() as HttpURLConnection

                client.requestMethod = method.name

                if (method == Companion.method.PUT || method == Companion.method.POST) {
                    client.setRequestProperty("content-type", "application/json")
                    client.outputStream.write(data.encodeToByteArray())
                }
                if (client.errorStream != null) {
                    client.errorStream.bufferedReader().use {
                        servicesJD.Error(it.readText(), client.responseCode)
                    }

                    return@launch
                }
                if (client.inputStream != null) {
                    client.inputStream.bufferedReader().use {
                        servicesJD.Finish(it.readText(), client.responseCode)
                    }

                    return@launch
                }
            }

        }
    }
}