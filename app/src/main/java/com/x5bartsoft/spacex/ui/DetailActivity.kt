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
        fragments.add(RocketFragment())
        fragments.add(LaunchpadFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Rocket")
        titles.add("Launchpad")

        val resultBundle = Bundle().apply {
            putParcelable(BUNDLE_LAUNCHES_KEY, args.result)
            putParcelable(BUNDLE_DETAILS_KEY, detailBundle)
        }

        val pagerAdapter = PagerAdapter(
            resultBundle,
            fragments,
            this
        )

        binding.aDetailViewPager.apply {
            adapter = pagerAdapter
            isUserInputEnabled = false
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

                    response.data?.let { Log.d("DetailActivity", "result: ${it.docs[0].rocket}") }

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
        val selectLaunches = Select(flightNumber = 1, name = 1, dateLocal = 1)
        val populateLaunches =
            listOf(Populate(Constants.QUERY_LAUNCHES, emptyList(), selectLaunches))

        val selectCapsules = Select(serial = 1, type = 1, id = 1)
        val capsules = Populate(Constants.QUERY_CAPSULES, populateLaunches, selectCapsules)

        //SHIPS
        val selectShips =
            Select(type = 1, roles = 1, image = 1, name = 1, id = 1, link = 1, yearBuilt = 1)
        val ships = Populate(Constants.QUERY_SHIPS, emptyList(), selectShips)

        //CORES
        val selectLandpad =
            Select(image = 1, name = 1, id = 1, fullName = 1, locality = 1, region = 1, details = 1)
        val landpad = Populate(Constants.QUERY_LANDPAD, emptyList(), selectLandpad)

//        val selectLaunches = Select(flightNumber = 1, name = 1, dateLocal = 1)
//        val populateLaunches =
//            listOf(Populate(Constants.QUERY_LAUNCHES, emptyList(), selectLaunches))

        val selectCore = Select(serial = 1, id = 1, reuseCount = 1)
        val core = Populate(Constants.QUERY_CORE, populateLaunches, selectCore)

        val populateCores = listOf(core, landpad)
        val cores = Populate(Constants.QUERY_CORES, populateCores, Select())

        //PAYLOADS
        val selectPayloads = Select(name = 1, type = 1, reused = 1, massKg = 1)
        val payloads = Populate(Constants.QUERY_PAYLOADS, emptyList(), selectPayloads)

        //ROCKET
        val selectRocket = Select(height = 1,
            diameter = 1,
            mass = 1,
            firstStage = 1,
            secondStage = 1,
            thrust = 1,
            payloads = 1,
            compositeFairing = 1,
            option1 = 1,
            reusable = 1,
            engines = 1,
            landingLegs = 1,
            payloadWeights = 1,
            flickrImages = 1,
            name = 1,
            active = 1,
            stages = 1,
            boosters = 1,
            costPerLaunch = 1,
            successRatePct = 1,
            firstFlight = 1,
            country = 1,
            company = 2,
            wikipedia = 1,
            description = 1,
            flightNumber = 1)
        val rocket = Populate(Constants.QUERY_ROCKET, emptyList(), selectRocket)


        //LAUNCHPAD
        val selectRockets = Select(name = 1)
        val rockets = Populate(Constants.QUERY_ROCKETS, emptyList(), selectRockets)

//        val selectLaunches = Select(flightNumber = 1, name = 1, dateLocal = 1)
        val launches = Populate(Constants.QUERY_LAUNCHES, emptyList(), selectLaunches)

        val populateLaunchpad = listOf(launches, rockets)
        val launchpad = Populate(Constants.QUERY_LAUNCHPAD, populateLaunchpad, Select())

        //OPTIONS
        val listPopulate = listOf(
            launchpad,
            rocket,
            payloads,
            cores,
            ships,
            capsules
        )

        val options = Options(listPopulate)
        val query = Query(args.result.flightNumber)

        Log.d("DetailActivity", "request :${LaunchDetailsRequest(options, query)}")
        return LaunchDetailsRequest(options, query)
    }


}