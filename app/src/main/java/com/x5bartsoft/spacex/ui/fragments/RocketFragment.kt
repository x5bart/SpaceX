package com.x5bartsoft.spacex.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.databinding.FragmentRocketBinding
import com.x5bartsoft.spacex.util.Constants
import com.x5bartsoft.spacex.util.NetworkResult
import com.x5bartsoft.spacex.viewmodels.MainViewModel


class RocketFragment : Fragment() {

    private var _binding: FragmentRocketBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel
    private var detailBundle: com.x5bartsoft.spacex.model.response.launchdetail.Doc? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRocketBinding.inflate(layoutInflater, container, false)
        val args = arguments
        detailBundle = args?.getParcelable(Constants.BUNDLE_DETAILS_KEY)
        requestApiData(detailBundle!!.rocket.id)
        return binding.root

    }

    private fun requestApiData(id: String) {
        Log.d("RocketFragment", "requestApiData called! id:$id")
        mainViewModel.getRocket(id)
        mainViewModel.rocketResponse.observe(requireActivity(), { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { Log.d("RocketFragment", "result: $it") }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> Log.d("RocketFragment", "Loading")
            }
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}