package com.x5bartsoft.spacex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.adapters.LaunchesAdapter
import com.x5bartsoft.spacex.adapters.RocketBottomSheetAdapter
import com.x5bartsoft.spacex.databinding.BottomSheetLaunchesBinding


class LaunchesBottomSheet : BottomSheetDialogFragment() {
    private var _bindind: BottomSheetLaunchesBinding? = null
    private val binding get() = _bindind!!

    private val mAdapter by lazy { RocketBottomSheetAdapter() }
    private var items = listOf("Falcon 1", "Falcon 9", "Falcon Heavy", "Starship")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _bindind = BottomSheetLaunchesBinding.inflate(layoutInflater, container, false)

        mAdapter.setData(items)
        binding.bsLaunchesRocketsRecyclerView.adapter = mAdapter

        return binding.root
    }

    override fun onDestroy() {
        _bindind = null
        super.onDestroy()
    }

}