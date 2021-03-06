package com.x5bartsoft.spacex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.adapters.LaunchesAdapter
import com.x5bartsoft.spacex.adapters.ShipAdapter
import com.x5bartsoft.spacex.databinding.FragmentLaunchpadBinding
import com.x5bartsoft.spacex.databinding.FragmentShipBinding
import com.x5bartsoft.spacex.model.response.launchdetail.Doc
import com.x5bartsoft.spacex.util.Constants
import com.x5bartsoft.spacex.viewmodels.MainViewModel


class ShipFragment : Fragment() {
    private var _binding: FragmentShipBinding? = null
    private val binding get() = _binding!!

    private var detailBundle: Doc? = null

    private val mAdapter by lazy { ShipAdapter() }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentShipBinding.inflate(layoutInflater, container, false)

        val args = arguments
        detailBundle = args?.getParcelable(Constants.BUNDLE_DETAILS_KEY)

        setupRecyclerView()

        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun setupRecyclerView() {
        mAdapter.setData(detailBundle!!.ships)
        binding.fShipRecyclerView.adapter = mAdapter
        binding.fShipRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}