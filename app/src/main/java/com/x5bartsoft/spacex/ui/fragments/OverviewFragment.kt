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
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.x5bartsoft.spacex.adapters.GalleryAdapter
import kotlin.math.abs


@AndroidEntryPoint
class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    private var launchBundle: Doc? = null
    private var detailBundle: com.x5bartsoft.spacex.model.response.launchdetail.Doc? = null

    private lateinit var galleryItemList: ArrayList<String>
    private lateinit var galleryAdapter: GalleryAdapter
    private lateinit var galleryHandle: Handler
    private lateinit var galleryRunnable: Runnable


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

        sliderItem()



        return binding.root
    }


    private fun sliderItem() {
        galleryItemList = ArrayList()
        getImageList()
        galleryAdapter = GalleryAdapter()
        val viewPager = binding.fOverviewGalleryViewPager
        galleryAdapter.setData(galleryItemList, viewPager)

        viewPager.adapter = galleryAdapter
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r: Float = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        viewPager.setPageTransformer(compositePageTransformer)
        galleryHandle = Handler()
        galleryRunnable = Runnable {
            viewPager.currentItem = viewPager.currentItem + 1
        }
        viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    galleryHandle.removeCallbacks(galleryRunnable)
                    galleryHandle.postDelayed(galleryRunnable, 1000)
                }
            }

        )

    }

    override fun onPause() {
        super.onPause()
        galleryHandle.removeCallbacks(galleryRunnable)

    }

    override fun onResume() {
        super.onResume()
        galleryHandle.postDelayed(galleryRunnable, 2000)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun bindImageView() {
        if (detailBundle != null) {
            bind(binding.fOverviewArticleImageView, detailBundle!!.links.article)
            bind(binding.fOverviewYoutubeImageView, detailBundle!!.links.webcast)
            bind(binding.fOverviewPressKitImageView, detailBundle!!.links.presskit)
            bind(binding.fOverviewRedditImageView, detailBundle!!.links.reddit.campaign)
            bind(binding.fOverviewWikiImageView, detailBundle!!.links.wikipedia)
        }
    }

    private fun bind(view: ImageView, url: String) {
        view.setOnClickListener {
            if (url.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }
    }

    private fun getImageList() {
        for (i in detailBundle!!.links.flickr.original) {
            galleryItemList.add(i)
        }
    }


}