package com.talhakasikci.kisileruygulamasi.UI.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.talhakasikci.kisileruygulamasi.R
import com.talhakasikci.kisileruygulamasi.UI.viewModel.KisiDetayViewModel
import com.talhakasikci.kisileruygulamasi.databinding.FragmentEditPersonBinding


class EditPersonFragment : Fragment() {
    private lateinit var binding: FragmentEditPersonBinding
    private lateinit var viewModel:KisiDetayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: KisiDetayViewModel by viewModels()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditPersonBinding.inflate(layoutInflater, container, false)
        val bundle: EditPersonFragmentArgs by navArgs()
        val gelenKisi = bundle.kisi

        binding.KisiNoET.setText(gelenKisi.kisi_tel)
        binding.KisiAdiET.setText(gelenKisi.kisi_ad)

        binding.KisiGuncelleButton.setOnClickListener {
            val kisi_ad = binding.KisiAdiET.text.toString()
            val kisi_tel = binding.KisiNoET.text.toString()
            guncelle(gelenKisi.kisi_id, kisi_ad, kisi_tel)
        }

        return binding.root
    }

    fun guncelle(kisi_id:Int, kisi_ad: String, kisi_tel: String) {
        // Güncelleme işlemleri burada yapılacak
        // Örneğin, veritabanına güncellenmiş verileri kaydedebilirsiniz

        Log.e("kisi güncelle", "Kisi ID: $kisi_id Kisi Adi: $kisi_ad Kisi No: $kisi_tel")
    }



}