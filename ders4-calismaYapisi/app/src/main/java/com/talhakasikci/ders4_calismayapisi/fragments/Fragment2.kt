package com.talhakasikci.ders4_calismayapisi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.talhakasikci.ders4_calismayapisi.R
import com.talhakasikci.ders4_calismayapisi.databinding.Fragment2Binding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class Fragment2 : Fragment() {
    private lateinit var binding: Fragment2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Fragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        // Fragment2'deki butona tıklandığında Fragment1'e geri dön

        val bundle : Fragment2Args by navArgs()
        val mesaj = "${bundle.message} : ${bundle.positiveNumber}"

        binding.fragment2Tv.text = mesaj

        binding.fragment2ToFragment1Button.setOnClickListener {
            val action = Fragment2Directions.actionFragment2ToFragment1()
            findNavController().navigate(action)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("Logs", "Fragment2 onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.e("Logs", "Fragment2 onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Logs", "Fragment2 onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.e("Logs", "Fragment2 onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Logs", "Fragment2 onDestroy")
    }


}