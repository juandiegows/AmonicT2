package com.example.amonict2.helper

class ResponseJD {
    var responseTextJD= ""
    var statusJD = 1
    var dataJD:Any = Any()
    var isSucessfulJD:Boolean = false
    override fun toString(): String {
        return "ResponseJD(responseText='$responseTextJD', status=$statusJD, data=$dataJD, isSucessful=$isSucessfulJD)"
    }


}