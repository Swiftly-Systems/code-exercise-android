package com.swiftly.codingexersicejavi.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.swiftly.codingexersicejavi.R
import com.swiftly.codingexersicejavi.framework.ManagerSpecialsViewModel
import kotlinx.android.synthetic.main.fragment_specials_list.*


class ManagerSpecialsFragment : Fragment() {

    private val TAG: String = "ManageSpecialFrag"
    private val specialsListAdapter = ManagerSpecialsAdapter()
    private lateinit var viewModel: ManagerSpecialsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specials_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(requireActivity()).get(ManagerSpecialsViewModel::class.java)

        observeViewModel()

        list_specials_view.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = specialsListAdapter
        }

    }

    private fun observeViewModel() {
        viewModel.specialsList.observe(ManagerSpecialFragment@ this, Observer { specials ->
           Log.d(TAG, ""+specials?.get(0)?.display_name)
            if (!specials.isNullOrEmpty())
                specialsListAdapter.submitList(specials)
        })

        viewModel.loading.observe(ManagerSpecialFragment@ this, Observer { b ->
            if (b)
                progress_loading.visibility = View.VISIBLE
            else
                progress_loading.visibility = View.GONE
        })

        viewModel.error.observe(ManagerSpecialFragment@ this, Observer { e ->
            showErrorAlert(e)
        })
    }

    private fun showErrorAlert(msg: String?){
        if (msg.isNullOrEmpty())
            return
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage(msg)
        builder.setNeutralButton("Ok") { dialog, which -> }
        builder.show()
    }

}