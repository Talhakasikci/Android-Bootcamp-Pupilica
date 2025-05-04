package com.talhakasikci.odev4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.talhakasikci.odev4.R
import com.talhakasikci.odev4.databinding.FragmentBBinding


class FragmentB : Fragment() {

    private lateinit var binding:FragmentBBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.GoToYButton.setOnClickListener {
            val action = FragmentBDirections.actionFragmentBToFragmentY()
            requireView().findNavController().navigate(action)
        }
    }


}