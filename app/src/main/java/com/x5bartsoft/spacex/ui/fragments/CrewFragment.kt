package com.x5bartsoft.spacex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.adapters.CapsulesAdapter
import com.x5bartsoft.spacex.adapters.CrewAdapter
import com.x5bartsoft.spacex.databinding.FragmentCapsulesBinding
import com.x5bartsoft.spacex.databinding.FragmentCrewBinding
import com.x5bartsoft.spacex.util.Constants


class CrewFragment : Fragment() {

    private var _binding: FragmentCrewBinding? = null
    private val binding get() = _binding!!

    private var detailBundle: com.x5bartsoft.spacex.model.response.launchdetail.Doc? = null

    private val mAdapter by lazy { CrewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCrewBinding.inflate(layoutInflater,container,false)

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
        mAdapter.setData(detailBundle!!.crew)
        binding.fCrewRecyclerView.adapter = mAdapter
        binding.fCrewRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
    }


}