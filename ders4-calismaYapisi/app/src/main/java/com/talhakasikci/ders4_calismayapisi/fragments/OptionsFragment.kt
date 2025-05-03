package com.talhakasikci.ders4_calismayapisi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.talhakasikci.ders4_calismayapisi.R
import com.talhakasikci.ders4_calismayapisi.databinding.FragmentOptionsBinding


class OptionsFragment : Fragment() {
    private lateinit var binding:FragmentOptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentOptionsBinding.inflate(inflater, container, false)

        binding.ButtonGoster.setOnClickListener {
            val action = OptionsFragmentDirections.actionOptionsFragmentToBottomSheetFragment()
            Navigation.findNavController(it).navigate(action)
        }

        return binding.root

    }


}