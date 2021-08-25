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
import androidx.recyclerview.widget.LinearLayoutManager
import com.x5bartsoft.spacex.viewmodels.MainViewModel
import com.x5bartsoft.spacex.adapters.LaunchesAdapter
import com.x5bartsoft.spacex.databinding.FragmentLaunchesBinding
import com.x5bartsoft.spacex.util.NetworkResult
import com.x5bartsoft.spacex.util.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LaunchesFragment : Fragment() {

    private var _binding: FragmentLaunchesBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy { LaunchesAdapter() }
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLaunchesBinding.inflate(inflater, container, false)

        setupRecyclerView()
        requestApiRockets()


        return binding.root
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readLaunches.observeOnce(
                viewLifecycleOwner, { database ->
                    if (database.isNotEmpty()) {
                        Log.d("LaunchesFragment", "readDatabase called!")
                        mAdapter.setData(database[0].launches)
                        hideShimmerEffect()
                    } else {
                        requestApiData()
                    }
                }
            )
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


    private fun requestApiData() {
        Log.d("LaunchesFragment", "requestApiData called!")
        mainViewModel.getLaunches()
        mainViewModel.launchesResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> showShimmerEffect()
            }
        })
    }

    private fun requestApiRockets() {
        mainViewModel.getRockets()
        mainViewModel.rocketsResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    requestApiLaunchpads()
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> Log.d("MainActivity", "loading")
            }
        })
    }

    private fun requestApiLaunchpads() {
        mainViewModel.getLaunchpads()
        mainViewModel.launchpadsResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    readDatabase()
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> Log.d("MainActivity", "loading")
            }
        })
    }


    private fun setupRecyclerView() {
        binding.fLaunchesRecyclerView.adapter = mAdapter
        binding.fLaunchesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }


    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readLaunches.observe(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
                    mAdapter.setData(database[0].launches)
                }
            })
        }

    }

    private fun showShimmerEffect() {
        binding.fLaunchesShimmerLayout.visibility = View.VISIBLE
        binding.fLaunchesShimmerLayout.startShimmer()
        binding.fLaunchesRecyclerView.visibility = View.GONE
    }

    private fun hideShimmerEffect() {
        binding.fLaunchesShimmerLayout.visibility = View.GONE
        binding.fLaunchesShimmerLayout.stopShimmer()
        binding.fLaunchesRecyclerView.visibility = View.VISIBLE

    }


}