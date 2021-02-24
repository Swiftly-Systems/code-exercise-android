package com.swiftly.codingexersicejavi.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.swiftly.codingexersicejavi.R
import com.swiftly.codingexersicejavi.framework.ManagerSpecialsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_specials_list.*


class ManagerSpecialsFragment : Fragment() {

    private val specialsListAdapter = ManagerSpecialsAdapter(arrayListOf())
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

        viewModel = ViewModelProviders.of(this).get(ManagerSpecialsViewModel::class.java)
        viewModel.getSpecials()
        observeViewModel()

        list_specials_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = specialsListAdapter
        }

    }

    private fun observeViewModel(){
        viewModel.specialsList.observe(ManagerSpecialFragment@this, Observer{ specials ->
            progress_loading.visibility = View.GONE
            specialsListAdapter.update(specials)
        })
    }

}