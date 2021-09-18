package com.x5bartsoft.spacex.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.x5bartsoft.spacex.adapters.RocketBottomSheetAdapter
import com.x5bartsoft.spacex.databinding.BottomSheetLaunchesBinding
import com.x5bartsoft.spacex.util.Constants.Companion.DEFAULT_LAUNCHPAD
import com.x5bartsoft.spacex.util.Constants.Companion.DEFAULT_ROCKETS
import com.x5bartsoft.spacex.viewmodels.LaunchesViewModel
import kotlin.Exception
import androidx.core.view.forEach
import androidx.navigation.fragment.findNavController
import com.x5bartsoft.spacex.util.Constants.Companion.LAUNCHPADS_ID
import com.x5bartsoft.spacex.util.Constants.Companion.ROCKETS_ID
import com.x5bartsoft.spacex.util.Constants.Companion.ROCKETS_NAME


class LaunchesBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetLaunchesBinding? = null
    private val binding get() = _binding!!

    private lateinit var launchViewModel: LaunchesViewModel
    private val mAdapter by lazy { RocketBottomSheetAdapter() }
    private var items = listOf("Falcon 1", "Falcon 9", "Falcon Heavy", "Starship")

    private var rockets = DEFAULT_ROCKETS
    private var rocketsId = mutableSetOf<String>()
    private var launchpads = DEFAULT_LAUNCHPAD
    private var launchpadsId = mutableSetOf<String>()
    private var success = ""
    private var successId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        launchViewModel = ViewModelProvider(requireActivity()).get(LaunchesViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = BottomSheetLaunchesBinding.inflate(layoutInflater, container, false)

        mAdapter.setData(items)
        binding.bsLaunchesRocketsRecyclerView.adapter = mAdapter

        launchViewModel.readQueryFilter.asLiveData().observe(viewLifecycleOwner, { value ->
            rockets = value.selectedRockets!!.toMutableSet()
            launchpads = value.selectedLaunchpads!!.toMutableSet()
            success = value.selectedSuccess.toString()
            updateChipSet(value.selectedRocketsId, binding.bsLaunchesRocketsChipGroup)
            updateChipSet(value.selectedLaunchpadsId, binding.bsLaunchesLaunchpadsChipGroup)
            updateChip(value.selectedSuccessId, binding.bsLaunchesSuccessChipGroup)

        })

        binding.bsLaunchesRocketsChipGroup.forEach { child ->
            (child as? Chip)?.setOnCheckedChangeListener { group, _ ->
                val chip = group.findViewById<Chip>(child.id)
                val selectedRockets = ROCKETS_ID[chip.text.toString()]
                if (chip.isChecked) {
                    rockets.add(selectedRockets!!)
                    rocketsId.add(child.id.toString())
                } else {
                    rockets.remove(selectedRockets)
                    rocketsId.remove(child.id.toString())
                }
            }
        }






        binding.bsLaunchesLaunchpadsChipGroup.forEach { child ->
            (child as? Chip)?.setOnCheckedChangeListener { group, _ ->
                val chip = group.findViewById<Chip>(child.id)
                val selectedLaunchpads = LAUNCHPADS_ID[chip.text.toString()]
                if (chip.isChecked) {
                    launchpads.add(selectedLaunchpads!!)
                    launchpadsId.add(child.id.toString())
                } else {
                    launchpads.remove(selectedLaunchpads)
                    launchpadsId.remove(child.id.toString())
                }
            }
        }

        binding.bsLaunchesSuccessChipGroup.setOnCheckedChangeListener { group, checkedId ->
            Log.d("LaunchesBottomSheet", "successId saved: $successId successId clicked:$checkedId")
            val chip = group.findViewById<Chip>(checkedId)
            if (checkedId != -1) {
                val selectedSuccess = when (chip.text.toString()) {
                    "Only success" -> "true"
                    "Only failure" -> "false"
                    else -> "null"
                }
                success = selectedSuccess
                successId = checkedId
                Log.d("LaunchesBottomSheet", "successId saved: $successId")
            } else {
                success = "null"
                successId = 1
                binding.bsLaunchesAllChip.isChecked = true
            }

        }

        binding.bsLaunchesApplyButton.setOnClickListener {
            launchViewModel.saveQueryFilter(
                rockets.toSet(),
                rocketsId,
                launchpads.toSet(),
                launchpadsId,
                success,
                successId
            )

            val action =
                LaunchesBottomSheetDirections.actionLaunchesBottomSheetToLaunchesFragment(true)
            findNavController().navigate(action)

        }

        return binding.root
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        if (chipId != 0) {
            Log.d("LaunchesBottomSheet", "updateChip: $chipId")
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            } catch (e: Exception) {
                Log.d("LaunchesBottomSheet", "exception updateChip: ${e.message.toString()}")
            }
        }

    }

    private fun updateChipSet(chipId: Set<String>, chipGroup: ChipGroup) {
        try {
            for (i in chipId) {
                chipGroup.findViewById<Chip>(i.toInt()).isChecked = true
            }
        } catch (e: Exception) {
            Log.d("LaunchesBottomSheet", "exception updateChipSet: ${e.message.toString()}")
        }


    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}


