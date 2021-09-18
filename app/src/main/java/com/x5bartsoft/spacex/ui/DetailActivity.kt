package com.x5bartsoft.spacex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.adapters.PagerAdapter
import com.x5bartsoft.spacex.databinding.ActivityDetailBinding
import com.x5bartsoft.spacex.model.request.launchdetails.*
import com.x5bartsoft.spacex.ui.fragments.LaunchpadFragment
import com.x5bartsoft.spacex.ui.fragments.OverviewFragment
import com.x5bartsoft.spacex.ui.fragments.RocketFragment
import com.x5bartsoft.spacex.util.Constants
import com.x5bartsoft.spacex.util.Constants.Companion.BUNDLE_DETAILS_KEY
import com.x5bartsoft.spacex.util.Constants.Companion.BUNDLE_LAUNCHES_KEY
import com.x5bartsoft.spacex.util.NetworkResult
import com.x5bartsoft.spacex.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailActivityArgs>()

    private lateinit var mainViewModel: MainViewModel
    private var detailBundle: Parcelable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setSupportActionBar(binding.aDetailToolBar)
        binding.aDetailToolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        requestApiData()


    }

    private fun setupTab() {
        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(LaunchpadFragment())
        fragments.add(RocketFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Launchpad")
        titles.add("Rocket")

        val resultBundle = Bundle()
        resultBundle.putParcelable(BUNDLE_LAUNCHES_KEY, args.result)
        resultBundle.putParcelable(BUNDLE_DETAILS_KEY, detailBundle)

        val pagerAdapter = PagerAdapter(
            resultBundle,
            fragments,
            this
        )

        binding.aDetailViewPager.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.aDetailsTabLayout, binding.aDetailViewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun requestApiData() {
        Log.d("DetailActivity", "requestApiData called!")
        mainViewModel.getLaunchesDetails(applyRequest())
        mainViewModel.launchesDetailsResponse.observe(this, { response ->
            when (response) {
                is NetworkResult.Success -> {

                    response.data?.let { Log.d("DetailActivity", "result: $it") }

                    detailBundle = response.data!!.docs[0]
                    setupTab()
                }
                is NetworkResult.Error -> {

                    Toast.makeText(this,
                        response.message.toString(),
                        Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> Log.d("DetailActivity", "Loading")
            }
        })
    }

    private fun applyRequest(): LaunchDetailsRequest {

        //CAPSULES
        val selectCapsules = Select(serial = 1, type = 1, id = 1)
        val capsules = Populate(path = Constants.QUERY_CAPSULES,
            select = selectCapsules,
            populate = emptyList())
        //SHIPS
        val selectShips =
            Select(type = 1, roles = 1, image = 1, name = 1, id = 1, link = 1, yearBuilt = 1)
        val ships =
            Populate(path = Constants.QUERY_SHIPS, select = selectShips, populate = emptyList())
        //CORES
        val selectLandpad =
            Select(image = 1, name = 1, id = 1, fullName = 1, locality = 1, region = 1, details = 1)
        val landpad =
            Populate(path = Constants.QUERY_LANDPAD, select = selectLandpad, populate = emptyList())
        val selectCore = Select(serial = 1, id = 1, reuse_count = 1)
        val core =
            Populate(path = Constants.QUERY_CORE, select = selectCore, populate = emptyList())
        val populateCores = listOf(core, landpad)
        val cores = Populate(path = Constants.QUERY_CORES, populate = populateCores, Select())
        //PAYLOADS
        val payloads = Populate(path = Constants.QUERY_PAYLOADS, populate = emptyList(), Select())
        //ROCKET
        val rocket = Populate(path = Constants.QUERY_ROCKET, emptyList(), Select(firstFlight = 1))
        //LAUNCHPAD
        val selectRockets = Select(name = 1)
        val rockets =
            Populate(path = Constants.QUERY_ROCKETS, select = selectRockets, populate = emptyList())
        val selectLaunches = Select(flightNumber = 1, name = 1, dateLocal = 1)
        val launches =
            Populate(Constants.QUERY_LAUNCHES, select = selectLaunches, populate = emptyList())
        val populateLaunchpad = listOf(launches, rockets)
        val launchpad =
            Populate(path = Constants.QUERY_LAUNCHPAD, populate = populateLaunchpad, Select())

        val listPopulate = listOf(launchpad, rocket, payloads, cores, ships, capsules)

        val selectLaunch =
            Select(name = 1,
                id = 1,
                dateLocal = 1,
                flightNumber = 1,
                details = 1,
                links = 1,
                success = 1)
        val options = Options(listPopulate, selectLaunch)
        val query = Query(args.result.name)

        Log.d("DetailActivity", "request :${LaunchDetailsRequest(options, query)}")
        return LaunchDetailsRequest(options, query)
    }


}