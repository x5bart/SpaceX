package com.x5bartsoft.spacex.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.adapters.GalleryRocketAdapter
import com.x5bartsoft.spacex.databinding.FragmentLaunchpadBinding
import com.x5bartsoft.spacex.databinding.FragmentRocketBinding
import com.x5bartsoft.spacex.model.ImageViewPager
import com.x5bartsoft.spacex.model.response.launchdetail.Doc
import com.x5bartsoft.spacex.util.Constants
import com.x5bartsoft.spacex.util.ZoomOutPageTransformer
import com.x5bartsoft.spacex.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchpadFragment : Fragment() {

    private var _binding: FragmentLaunchpadBinding? = null
    private val binding get() = _binding!!

    private var detailBundle: Doc? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLaunchpadBinding.inflate(layoutInflater, container, false)

        val args = arguments
        detailBundle = args?.getParcelable(Constants.BUNDLE_DETAILS_KEY)
        binding.detail = detailBundle

        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}