package com.x5bartsoft.spacex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.adapters.CoresAdapter
import com.x5bartsoft.spacex.adapters.GalleryRocketAdapter
import com.x5bartsoft.spacex.adapters.MassToOrbitAdapter
import com.x5bartsoft.spacex.databinding.FragmentCoresBinding
import com.x5bartsoft.spacex.databinding.FragmentRocketBinding
import com.x5bartsoft.spacex.model.ImageViewPager
import com.x5bartsoft.spacex.util.Constants
import com.x5bartsoft.spacex.viewmodels.MainViewModel

class CoresFragment : Fragment() {

    private var _binding: FragmentCoresBinding? = null
    private val binding get() = _binding!!

    private var detailBundle: com.x5bartsoft.spacex.model.response.launchdetail.Doc? = null

    private val mAdapter by lazy { CoresAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCoresBinding.inflate(layoutInflater, container, false)

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
        mAdapter.setData(detailBundle!!.cores)
        binding.fCoresRecyclerView.adapter = mAdapter
        binding.fCoresRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


}