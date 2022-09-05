package com.example.amonict2.ui.Amenities

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amonict2.Adapter.AdapterAmenitiees
import com.example.amonict2.R
import com.example.amonict2.databinding.FragmentAmenitiesBinding
import com.example.amonict2.model.Amenities

class AmenitiesFragment : Fragment() {


    lateinit var binding: FragmentAmenitiesBinding
    companion object {
        fun newInstance() = AmenitiesFragment()
    }

    private lateinit var viewModel: AmenitiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAmenitiesBinding.inflate(layoutInflater)

        binding.listBinding.adapter = AdapterAmenitiees(this.requireContext(),Amenities.Get())

        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AmenitiesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}