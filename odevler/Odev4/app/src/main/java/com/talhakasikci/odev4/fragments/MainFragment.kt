package com.talhakasikci.odev4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.talhakasikci.odev4.R
import com.talhakasikci.odev4.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.GoToAButton.setOnClickListener {

            val action  = MainFragmentDirections.actionMainFragmentToFragmentA()
            Navigation.findNavController(requireView()).navigate(action)
        }

        binding.GoToXButton.setOnClickListener {
            val action  = MainFragmentDirections.actionMainFragmentToFragmentX()
            Navigation.findNavController(requireView()).navigate(action)
        }
    }


}