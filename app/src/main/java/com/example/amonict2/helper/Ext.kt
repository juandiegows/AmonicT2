package com.example.amonict2.helper

import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.lang.reflect.Type
import kotlin.reflect.javaType
import kotlin.reflect.typeOf

@OptIn(ExperimentalStdlibApi::class)
fun JSONObject.toClass(name:String): Any{
    var onClass = Class.forName(name)
    var instancia = onClass.newInstance()
    onClass.declaredFields.forEach {
        it.isAccessible = true
        it.set(instancia,when(it.genericType){
            typeOf<Int>().javaType -> this.getInt(it.name)
            typeOf<Long>().javaType -> this.get(it.name)
            typeOf<String>().javaType -> this.getString(it.name)
            typeOf<Boolean>().javaType -> this.getBoolean(it.name)
            typeOf<Double>().javaType -> this.getDouble(it.name)

            else -> {
                try {
                    this.getJSONObject(it.name).toClass(it.type.name)
                }catch (e:Exception){

                }

            }
        })
    }
    return  instancia
}

fun JSONArray.toList(name:String): List<Any>{
    var lista = ArrayList<Any>()

    for (i in 0 until  this.length()){
        lista.add(this.getJSONObject(i).toClass(name))
    }
    return lista
}

inline fun <reified T> Any.Cast() :T {
    return this as T
}