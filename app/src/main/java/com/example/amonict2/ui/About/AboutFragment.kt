package com.example.amonict2.ui.About

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amonict2.R
import com.example.amonict2.databinding.FragmentAboutBinding
import com.example.amonict2.helper.Singleton
import com.example.amonict2.helper.textJD

class AboutFragment : Fragment() {

    companion object {
        fun newInstance() = AboutFragment()
    }

    lateinit var binding: FragmentAboutBinding
    private lateinit var viewModel: AboutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Singleton.App = requireActivity().application
        binding = FragmentAboutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AboutViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.message.observeForever {
            binding.txtText.textJD = it
        }

    }

}