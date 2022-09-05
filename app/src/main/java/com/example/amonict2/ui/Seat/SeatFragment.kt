package com.example.amonict2.ui.Seat

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amonict2.DAO.CallServiceJD
import com.example.amonict2.DAO.servicesJD
import com.example.amonict2.R
import com.example.amonict2.databinding.FragmentSeatBinding
import com.example.amonict2.helper.Cast
import com.example.amonict2.helper.textJD
import com.example.amonict2.helper.toClass
import com.example.amonict2.model.ticket
import org.json.JSONObject

class SeatFragment : Fragment() {

    companion object {
        fun newInstance() = SeatFragment()
    }

    lateinit var binding: FragmentSeatBinding
    private lateinit var viewModel: SeatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeatBinding.inflate(layoutInflater)
        binding.btnNext.setOnClickListener {
            FindTypeCabin(binding.txtNumberTickets.textJD.toInt())
        }
        return binding.root
    }

    private fun FindTypeCabin(tickets: Int) {
        CallServiceJD.StartQuery(
            "api/ticket/${tickets}",
            CallServiceJD.Companion.method.GET,
            "",
            object : servicesJD {
                override fun Finish(responseText: String, status: Int) {
                    var ticktet: ticket =
                        JSONObject(responseText).toClass(ticket::class.java.name).Cast()
                    this@SeatFragment.requireActivity().runOnUiThread {

                        if (ticktet.type == "economy") {
                            binding.imageView6.animate().translationX(1200f).start()
                            AlertDialog.Builder(this@SeatFragment.requireContext())
                                .setMessage("the option reserve is not enabled in the moment for the type cabin, you will to go to our offices for the reservation")
                                .setPositiveButton("Ok"
                                ) { _, _

                                    ->
                                }
                                .create()
                                .show()
                            binding.btnReserveSeat.isEnabled = false
                        } else if (ticktet.type == "business") {

                            binding.imageView6.animate().translationX(1200f).withEndAction{
                                binding.imageView6.setImageResource(R.drawable.business_class_seats)
                                binding.imageView6.animate().translationX(0f).start()
                            }

                            binding.btnReserveSeat.isEnabled = true
                        } else if (ticktet.type == "first") {
                            binding.btnReserveSeat.isEnabled = true

                            binding.imageView6.animate().translationX(1200f).withEndAction{
                                binding.imageView6.setImageResource(R.drawable.first_class)
                                binding.imageView6.animate().translationX(0f).start()
                            }

                        } else {
                            AlertDialog.Builder(this@SeatFragment.requireContext())
                                .setTitle("the option reserve is not enabled in the moment for the business and the economy  class")
                                .setPositiveButton("Ok"
                                ) { _, _

                                    ->
                                }
                                .create()
                                .show()
                            binding.btnReserveSeat.isEnabled = false
                        }
                    }

                }

                override fun Error(responseText: String, status: Int) {
                    TODO("Not yet implemented")
                }
            })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SeatViewModel::class.java)
        // TODO: Use the ViewModel
    }

}