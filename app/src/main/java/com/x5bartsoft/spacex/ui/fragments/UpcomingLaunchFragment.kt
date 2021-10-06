package com.x5bartsoft.spacex.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.x5bartsoft.spacex.databinding.FragmentUpcomingLaunchBinding
import com.x5bartsoft.spacex.model.request.nextlaunch.NextLaunchRequest
import com.x5bartsoft.spacex.util.NetworkResult
import com.x5bartsoft.spacex.util.observeOnce
import com.x5bartsoft.spacex.viewmodels.MainViewModel
import kotlinx.coroutines.launch

class UpcomingLaunchFragment : Fragment() {

    private var _binding: FragmentUpcomingLaunchBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpcomingLaunchBinding.inflate(layoutInflater, container, false)

        readDatabase()

        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readNextLaunchLaunch.observeOnce(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
                    Log.d("UpcomingLaunchFragment", "readDatabase called!")
//                    mAdapter.setData(database[0])
//                    hideShimmerEffect()
                    Log.d("UpcomingLaunchFragment", "read database: ${database[0]}")
                } else {
                    requestApiData()
                }
            }
            )
        }
    }

    private fun requestApiData() {
        Log.d("UpcomingLaunchFragment", "requestApiData called!")
        mainViewModel.getNextLaunch(NextLaunchRequest())
        mainViewModel.nextLaunch.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
//                    hideShimmerEffect()
                    response.data?.let {
//                        mAdapter.setData(it)
                        Log.d("UpcomingLaunchFragment", "response database: $it")
                    }
                }
                is NetworkResult.Error -> {
//                    hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
//                    showShimmerEffect()
                }
            }
        })
    }

    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readNextLaunchLaunch.observe(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
//                    mAdapter.setData(database[0])
                    Log.d("UpcomingLaunchFragment", "network error read database: ${database[0]}")
                }
            })
        }

    }


}