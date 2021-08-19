package com.x5bartsoft.spacex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.x5bartsoft.spacex.databinding.FragmentLaunchesBinding


class LaunchesFragment : Fragment() {

    private var _binding: FragmentLaunchesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLaunchesBinding.inflate(inflater, container, false)

        showShimmerEffect()

        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
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