package com.example.amonict2.ui.flights

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.amonict2.Adapter.AdapterFight
import com.example.amonict2.DAO.CallServiceJD
import com.example.amonict2.DAO.servicesJD
import com.example.amonict2.R
import com.example.amonict2.databinding.FragmentAmenitiesBinding
import com.example.amonict2.databinding.FragmentFlightsBinding
import com.example.amonict2.helper.Cast
import com.example.amonict2.helper.HTTP
import com.example.amonict2.helper.textJD
import com.example.amonict2.helper.toList
import com.example.amonict2.model.Port
import com.example.amonict2.model.schedule
import org.json.JSONArray
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

class FlightsFragment : Fragment() {

    companion object {
        fun newInstance() = FlightsFragment()
    }

    lateinit var binding: FragmentFlightsBinding
    private lateinit var viewModel: FlightsViewModel


    fun OpenDialogDate() {
        var calendar = Calendar.getInstance()
        var dialog = DatePickerDialog(
            this.requireContext(),
            DatePickerDialog.OnDateSetListener() { view, year, month, dayOfMonth ->
                binding.editTextDate.textJD = "$year-$month-$dayOfMonth"

            }, 2022, 8, 5

        ).show()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightsBinding.inflate(layoutInflater)
        eventos()
        return binding.root
    }



    private fun eventos() {
        binding.btnFecha.setOnClickListener {
            OpenDialogDate()
        }
        binding.editTextDate.setOnClickListener {
            OpenDialogDate()
        }

        binding.btnSearch.setOnClickListener {
            if (binding.editTextDate.textJD.isEmpty()) {
                AlertDialog.Builder(this.requireContext())
                    .setTitle("all field are required")
                    .setPositiveButton("Ok"
                    ) { _, _

                        ->
                    }
                    .create()
                    .show()
                return@setOnClickListener
            }


            viewModel.FindFlight(
                (binding.spnFrom.selectedItem as Port).id,
                (binding.spinner2.selectedItem as Port).id,
                binding.editTextDate.textJD
            )
        }
    }

    private fun observar() {
        viewModel.list.observeForever {

            Log.e("TAG", "eventos: $it")
            if (it.statusJD == HTTP.OK) {
                binding.listSchedule.adapter =
                    AdapterFight(requireContext(), it.dataJD.Cast(), binding.editTextDate.textJD)

            }
        }
        viewModel.listPort.observeForever {
            if(it.statusJD == HTTP.OK){
                var list:List<Port> = it.dataJD.Cast()
                binding.spnFrom.adapter = ArrayAdapter(
                   requireActivity().application,
                    android.R.layout.simple_expandable_list_item_1,
                    list
                )
                binding.spinner2.adapter = ArrayAdapter(
                    requireActivity().application,
                    android.R.layout.simple_expandable_list_item_1,
                    list
                )
            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FlightsViewModel::class.java)
        // TODO: Use the ViewModel
        observar()
        viewModel.FillSpinnerAirport()
    }

}