package com.x5bartsoft.spacex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.x5bartsoft.spacex.databinding.FragmentOverviewBinding
import com.x5bartsoft.spacex.model.response.launches.Doc
import com.x5bartsoft.spacex.util.Constants.Companion.BUNDLE_DETAILS_KEY
import com.x5bartsoft.spacex.util.Constants.Companion.BUNDLE_LAUNCHES_KEY
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.x5bartsoft.spacex.adapters.GalleryAdapter
import com.x5bartsoft.spacex.model.ImageLaunch
import com.x5bartsoft.spacex.util.ZoomOutPageTransformer


@AndroidEntryPoint
class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    private var launchBundle: Doc? = null
    private var detailBundle: com.x5bartsoft.spacex.model.response.launchdetail.Doc? = null

    private lateinit var galleryItemList: ArrayList<ImageLaunch>
    private val galleryAdapter by lazy { GalleryAdapter() }


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

        bindImageView()

        getImageList()
        setupViewPager()



        return binding.root
    }


    private fun setupViewPager() {
        val viewPager = binding.fOverviewGalleryViewPager.apply {
            adapter = galleryAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(ZoomOutPageTransformer())
        }
        binding.fOverviewWormDotsIndicator.setViewPager2(viewPager)
        galleryAdapter.setData(galleryItemList, viewPager)
//        val tabLayout = binding.fOverviewTabLayout
//
//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//        }.attach()
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun bindImageView() {
        if (detailBundle != null) {
            bindLinksForImage(binding.fOverviewArticleImageView, detailBundle!!.links.article)
            bindLinksForImage(binding.fOverviewYoutubeImageView, detailBundle!!.links.webcast)
            bindLinksForImage(binding.fOverviewPressKitImageView, detailBundle!!.links.presskit)
            bindLinksForImage(binding.fOverviewRedditImageView,
                detailBundle!!.links.reddit.campaign)
            bindLinksForImage(binding.fOverviewWikiImageView, detailBundle!!.links.wikipedia)
        }
    }

    private fun bindLinksForImage(view: ImageView, url: String) {
        view.setOnClickListener {
            if (url.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }
    }

    private fun getImageList() {
        galleryItemList = ArrayList()
        val args = detailBundle!!
        val pathImage = args.links.patch.small
        val name = args.name
        val flightNumber = args.flightNumber
        val date = args.dateLocal
        val success = args.success
        for (i in detailBundle!!.links.flickr.original) {
            galleryItemList.add(ImageLaunch(pathImage, name, i, flightNumber, date, success))
        }
    }


}