package com.example.securityintegration.Views.OrgLookup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.securityintegration.Models.OrgLookup.MarginItemDecoration
import com.example.securityintegration.ViewModels.OrgLookup.OrgListViewModel
import com.example.securityintegration.databinding.OrgListFragmentBinding

class OrgListFragment : Fragment() {

    companion object {
        fun newInstance() = OrgListFragment()

    }

    private lateinit var binding: OrgListFragmentBinding
    private val viewModel: OrgListViewModel by viewModels()
    private val adapter = OrgListAdapter(arrayListOf())
    private val space = 20

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OrgListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Observers, events and adapter
        configObservers()
        configAdapter()
        configEvents()
        getOrgData()
    }

    private fun configEvents() {
        viewModel.getOrgs()
    }

    private fun configObservers() {
        viewModel.orgList.observe(viewLifecycleOwner) { orgList ->
            adapter.update(orgList)
        }
    }

    private fun configAdapter() {
        val recyclerView = binding.rvOrgList
        recyclerView.addItemDecoration(
            MarginItemDecoration(space)
        )
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun getOrgData ()
    {
        val recyclerView = binding.rvOrgList
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : OrgListAdapter.onItemClickListener{
            override fun onItemClick(pos: Int) {
                Toast.makeText(activity, pos, Toast.LENGTH_SHORT).show()
            }
        })
    }

}