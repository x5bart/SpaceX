package com.x5bartsoft.spacex.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.x5bartsoft.spacex.adapters.GalleryRocketAdapter
import com.x5bartsoft.spacex.adapters.LaunchesAdapter
import com.x5bartsoft.spacex.adapters.MassToOrbitAdapter
import com.x5bartsoft.spacex.databinding.FragmentRocketBinding
import com.x5bartsoft.spacex.model.ImageViewPager
import com.x5bartsoft.spacex.util.Constants
import com.x5bartsoft.spacex.util.ZoomOutPageTransformer
import com.x5bartsoft.spacex.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketFragment : Fragment() {

    private var _binding: FragmentRocketBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel
    private var detailBundle: com.x5bartsoft.spacex.model.response.launchdetail.Doc? = null

    private lateinit var galleryItemList: ArrayList<ImageViewPager>
    private val galleryAdapter by lazy { GalleryRocketAdapter() }
    private val mAdapter by lazy { MassToOrbitAdapter() }

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
        binding.rocket = detailBundle!!.rocket

        setupViewPager()
        setupRecyclerView()

        return binding.root

    }



    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun getImageList() {
        val response = detailBundle!!.rocket
        Log.d("RocketFragment", "detailBundle!!.rocket : ${response.flickrImages}")
        galleryItemList = ArrayList()
        val name = response.name
        val height = response.height.meters
        val diameter = response.diameter.meters
        val mass = response.mass.kg
        for (i in response.flickrImages) {
            galleryItemList.add(ImageViewPager(
                name = name,
                url = i,
                height = height,
                diameter = diameter,
                mass = mass
            ))
        }
    }

    private fun setupViewPager() {
        getImageList()
        val viewPager = binding.fRocketGalleryViewPager.apply {
            adapter = galleryAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(ZoomOutPageTransformer())
        }
        binding.fRocketWormDotsIndicator.setViewPager2(viewPager)
        galleryAdapter.setData(galleryItemList, viewPager)
    }

    private fun setupRecyclerView() {
        mAdapter.setData(detailBundle!!.rocket.payloadWeights)
        binding.fRocketMassToOrbitRecyclerView.adapter = mAdapter
        binding.fRocketMassToOrbitRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


}