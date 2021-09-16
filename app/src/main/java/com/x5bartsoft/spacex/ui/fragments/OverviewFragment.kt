package com.x5bartsoft.spacex.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.x5bartsoft.spacex.databinding.FragmentOverviewBinding
import com.x5bartsoft.spacex.model.request.launchdetails.*
import com.x5bartsoft.spacex.model.response.launches.Doc
import com.x5bartsoft.spacex.util.Constants
import com.x5bartsoft.spacex.util.Constants.Companion.BUNDLE_DETAILS_KEY
import com.x5bartsoft.spacex.util.Constants.Companion.BUNDLE_LAUNCHES_KEY
import com.x5bartsoft.spacex.util.NetworkResult
import com.x5bartsoft.spacex.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    private var launchBundle: Doc? = null
    private var detailBundle: com.x5bartsoft.spacex.model.response.launchdetail.Doc? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOverviewBinding.inflate(layoutInflater, container, false)

        val args = arguments
        launchBundle = args?.getParcelable(BUNDLE_LAUNCHES_KEY)
        detailBundle = args?.getParcelable(BUNDLE_DETAILS_KEY)


        binding.result = launchBundle
        binding.detail = detailBundle
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }




}