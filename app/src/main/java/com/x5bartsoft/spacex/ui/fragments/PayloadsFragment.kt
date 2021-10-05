package com.x5bartsoft.spacex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.x5bartsoft.spacex.R
import com.x5bartsoft.spacex.adapters.CoresAdapter
import com.x5bartsoft.spacex.adapters.PayloadsAdapter
import com.x5bartsoft.spacex.databinding.FragmentCoresBinding
import com.x5bartsoft.spacex.databinding.FragmentPayloadsBinding
import com.x5bartsoft.spacex.model.response.launchdetail.Doc
import com.x5bartsoft.spacex.util.Constants


class PayloadsFragment : Fragment() {

    private var _binding: FragmentPayloadsBinding? = null
    private val binding get() = _binding!!

    private var detailBundle: Doc? = null

    private val mAdapter by lazy { PayloadsAdapter() }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPayloadsBinding.inflate(layoutInflater,container,false)

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
        mAdapter.setData(detailBundle!!.payloads)
        binding.fPayloadsRecyclerView.adapter = mAdapter
        binding.fPayloadsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}