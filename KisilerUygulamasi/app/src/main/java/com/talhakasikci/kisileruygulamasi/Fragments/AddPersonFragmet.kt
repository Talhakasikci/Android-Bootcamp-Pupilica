package com.talhakasikci.kisileruygulamasi.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.talhakasikci.kisileruygulamasi.R
import com.talhakasikci.kisileruygulamasi.databinding.FragmentAddPersonFragmetBinding


class AddPersonFragmet : Fragment() {
    private lateinit var binding: FragmentAddPersonFragmetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddPersonFragmetBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.KisiEkleButton.setOnClickListener {
            val kisi_adi = binding.KisiAdiET.text.toString()

            val kisi_no = binding.KisiNoET.text.toString()
            userAdd(kisi_adi, kisi_no)
        }

    }

    fun userAdd(kisiAd:String,kisiNo:String){
        Log.e("kisi Kaydet", "Kisi Adi: $kisiAd Kisi No: $kisiNo")
    }

}