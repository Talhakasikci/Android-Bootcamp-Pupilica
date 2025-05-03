package com.talhakasikci.ders4_calismayapisi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.talhakasikci.ders4_calismayapisi.R
import com.talhakasikci.ders4_calismayapisi.databinding.Fragment1Binding


class Fragment1 : Fragment() {
   private lateinit var binding:Fragment1Binding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Fragment1Binding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragment1ToFragment2Button.setOnClickListener {
            val action = Fragment1Directions.actionFragment1ToFragment2(message = "zürafalar çok uzun", positiveNumber = 10)

            findNavController().navigate(action)
            }

        }

    }


