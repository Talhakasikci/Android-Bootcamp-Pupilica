package com.talhakasikci.kisileruygulamasi.UI.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.talhakasikci.kisileruygulamasi.R
import com.talhakasikci.kisileruygulamasi.UI.viewModel.KisiEkleViewModel
import com.talhakasikci.kisileruygulamasi.databinding.FragmentAddPersonFragmetBinding

class AddPersonFragmet : Fragment() {
    private lateinit var binding: FragmentAddPersonFragmetBinding
    private val viewModel: KisiEkleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPersonFragmetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.KisiEkleButton.setOnClickListener {
            val kisiAdi = binding.KisiAdiET.text.toString()
            val kisiTel = binding.KisiNoET.text.toString()
            
            if (kisiAdi.isNotEmpty() && kisiTel.isNotEmpty()) {
                viewModel.kaydet(kisiAdi, kisiTel)
                // Navigate back to main fragment
                findNavController().navigateUp()
            }
        }
    }

    fun userAdd(kisiAd:String,kisiNo:String){

    }

}