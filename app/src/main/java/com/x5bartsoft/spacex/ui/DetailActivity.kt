package com.x5bartsoft.spacex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.adapters.PagerAdapter
import com.x5bartsoft.spacex.data.database.etities.FavoriteEntity
import com.x5bartsoft.spacex.databinding.ActivityDetailBinding
import com.x5bartsoft.spacex.model.request.launchdetails.*
import com.x5bartsoft.spacex.ui.fragments.LaunchpadFragment
import com.x5bartsoft.spacex.ui.fragments.OverviewFragment
import com.x5bartsoft.spacex.ui.fragments.RocketFragment
import com.x5bartsoft.spacex.ui.fragments.ShipFragment
import com.x5bartsoft.spacex.util.Constants
import com.x5bartsoft.spacex.util.Constants.Companion.BUNDLE_DETAILS_KEY
import com.x5bartsoft.spacex.util.Constants.Companion.BUNDLE_LAUNCHES_KEY
import com.x5bartsoft.spacex.util.NetworkResult
import com.x5bartsoft.spacex.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailActivityArgs>()

    private lateinit var mainViewModel: MainViewModel
    private var detailBundle: Parcelable? = null
    private lateinit var menuItem: MenuItem
    private var launchSaved = false
    private var savedLaunchId = 0


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
        fragments.add(ShipFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Rocket")
        titles.add("Launchpad")
        titles.add("Ships")

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
        } else if (item.itemId == R.id.save_to_favorite_menu && !launchSaved) {
            saveToFavorites(item)
        } else if (item.itemId == R.id.save_to_favorite_menu && launchSaved) {
            removeFromFavorites(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        menuItem = menu!!.findItem(R.id.save_to_favorite_menu)
        checkSavedItem(menuItem)
        return true
    }

    override fun onDestroy() {
        _binding = null
        changeMenuItemColor(menuItem, R.color.white)
        super.onDestroy()
    }

    private fun requestApiData() {
        Log.d("DetailActivitylog", "requestApiData called!")
        mainViewModel.getLaunchesDetails(applyRequest())
        mainViewModel.launchesDetailsResponse.observe(this, { response ->
            when (response) {
                is NetworkResult.Success -> {

                    response.data?.let {
                        detailBundle = response.data.docs[0]
                        setupTab()
                    }


                }
                is NetworkResult.Error -> {

                    Toast.makeText(this,
                        response.message.toString(),
                        Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> Log.d("DetailActivitylog", "Loading")
            }
        })
    }

    private fun checkSavedItem(menuItem: MenuItem) {
        mainViewModel.readFavoriteLaunch.observe(this, { favoritesEntity ->
            try {
                for (savedLaunch in favoritesEntity) {
                    Log.d("DetailActivitylog",
                        "saved launch: ${savedLaunch.launch.flightNumber}  args launch: ${args.result.flightNumber}")
                    if (savedLaunch.launch.flightNumber == args.result.flightNumber) {
                        changeMenuItemColor(menuItem, R.color.yellow)
                        savedLaunchId = savedLaunch.id
                        launchSaved = true
                    }
                }
            } catch (e: Exception) {
                Log.d("DetailActivitylog", e.message.toString())
            }
        })
    }

    private fun saveToFavorites(item: MenuItem) {
        val favoriteEntity =
            FavoriteEntity(
                0,
                args.result
            )
        mainViewModel.insertFavoriteLaunch(favoriteEntity)
        changeMenuItemColor(item, R.color.yellow)
        showSnackBar("Recipe saved.")
        launchSaved = true
    }

    private fun removeFromFavorites(item: MenuItem) {
        val favoriteEntity =
            FavoriteEntity(
                savedLaunchId,
                args.result
            )
        mainViewModel.deleteFavoriteLaunch(favoriteEntity)
        changeMenuItemColor(item, R.color.white)
        showSnackBar("Removed from Favorites.")
        launchSaved = false
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(this, color))
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.aDetailLayout,
            message,
            Snackbar.LENGTH_SHORT
        ).setAction("Okay") {}
            .show()

    }


    private fun applyRequest(): LaunchDetailsRequest {

        //CORES
        val landpad = PopulateX(Constants.QUERY_LANDPAD)
        val core = PopulateX(Constants.QUERY_CORE)

        val populateCores = listOf(core, landpad)
        val cores = Populate(Constants.QUERY_CORES, populateCores)

        //LAUNCHPAD
        val rockets = PopulateX(Constants.QUERY_ROCKETS)
        val launches = PopulateX(Constants.QUERY_LAUNCHES)

        val populateLaunchpad = listOf(launches, rockets)
        val launchpad = Populate(Constants.QUERY_LAUNCHPAD, populateLaunchpad)

        //PAYLOADS
        val payloads = Populate(Constants.QUERY_PAYLOADS, emptyList())

        //CAPSULES
        val populateLaunchesCapsule = listOf(PopulateX(Constants.QUERY_LAUNCHES))
        val capsules = Populate(Constants.QUERY_CAPSULES, populateLaunchesCapsule)

        //SHIPS
        val ships = Populate(Constants.QUERY_SHIPS, emptyList())

        //ROCKET
        val rocket = Populate(Constants.QUERY_ROCKET, emptyList())

        //OPTIONS
        val listPopulate = listOf(
            rocket,
            ships,
            capsules,
            payloads,
            launchpad,
            cores
        )

        val options = Options(listPopulate)
        val query = Query(args.result.flightNumber)

        return LaunchDetailsRequest(options, query)
    }


}