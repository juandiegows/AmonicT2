package com.example.amonict2.ui.flights

import android.R
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amonict2.Adapter.AdapterFight
import com.example.amonict2.DAO.CallServiceJD
import com.example.amonict2.DAO.servicesJD
import com.example.amonict2.helper.Cast
import com.example.amonict2.helper.ResponseJD
import com.example.amonict2.helper.toList
import com.example.amonict2.model.Port
import com.example.amonict2.model.schedule
import org.json.JSONArray

class FlightsViewModel : ViewModel() {


    private val _list = MutableLiveData<ResponseJD>().apply {
        this.value = ResponseJD()
    }
    val list: LiveData<ResponseJD> = _list

    private val _listPort = MutableLiveData<ResponseJD>().apply {
        this.value = ResponseJD()
    }
    val listPort: LiveData<ResponseJD> = _listPort
  fun FillSpinnerAirport() {
        Log.e("HOla", "FillSpinnerAirport: HOla" )
        CallServiceJD.StartQuery("api/port/list",
            CallServiceJD.Companion.method.GET,
            "",
            object : servicesJD {
                override fun Finish(responseText: String, status: Int) {
                    Log.e("Finish", "Finish ($status): => $responseText")


                        var list:List<Port> = JSONArray(responseText).toList(Port::class.java.name).Cast()


                    _listPort.postValue(ResponseJD().apply {
                        this.responseTextJD = responseText
                        this.statusJD = status
                        this.dataJD = list
                        this.isSucessfulJD = true
                    })


                }

                override fun Error(responseText: String, status: Int) {
                    Log.e("Error", "Error ($status): => $responseText")
                    _listPort.postValue(ResponseJD().apply {
                        this.responseTextJD = responseText
                        this.statusJD = status
                        this.dataJD = Any().Cast<List<Port>>()
                        this.isSucessfulJD = true
                    })
                }
            }
        )
    }
    fun FindFlight(from: Int, to: Int, Fecha: String) {
        CallServiceJD.StartQuery("api/schedule/list?from=$from&to=$to&date=$Fecha",
            CallServiceJD.Companion.method.GET,
            "",
            object : servicesJD {
                override fun Finish(responseText: String, status: Int) {
                    Log.e("Finish", "Finish: $status $responseText")

                    var list: List<schedule> =
                        JSONArray(responseText).toList(schedule::class.java.name).Cast()
                    _list.postValue(ResponseJD().apply {
                        this.responseTextJD = responseText
                        this.statusJD = status
                        this.dataJD = list
                        this.isSucessfulJD = true
                    })


                }

                override fun Error(responseText: String, status: Int) {
                    Log.e("Error", "Error: $status")
                    _list.postValue(ResponseJD().apply {
                        this.responseTextJD = responseText
                        this.statusJD = status
                        this.dataJD = Any().Cast<List<schedule>>()
                        this.isSucessfulJD = false
                    })
                }
            }
        )
    }

}