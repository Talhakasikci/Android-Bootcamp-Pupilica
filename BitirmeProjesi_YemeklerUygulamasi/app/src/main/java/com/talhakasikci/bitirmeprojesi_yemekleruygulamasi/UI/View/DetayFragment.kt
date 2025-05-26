package com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.UI.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.R
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.UI.viewModel.YemeklerViewModel
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.models.Yemek
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.databinding.FragmentDetayBinding


class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private val viewModel: YemeklerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetayBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val secilenYemek = arguments?.getParcelable<Yemek>("secilenYemek") as Yemek


        binding.YemekAdi.text  = secilenYemek.yemek_adi
        binding.yemekFiyat.text = "${secilenYemek.yemek_fiyat} TL"
        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${secilenYemek.yemek_resim_adi}"
        Glide.with(requireContext())
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.yemekResim)

        // Varsayılan adet değeri
        binding.YemekAdet.setText("1")

        binding.btnPlus.setOnClickListener {
            val currentPiece = binding.YemekAdet.text.toString().toIntOrNull() ?: 0
            binding.YemekAdet.setText((currentPiece+1).toString())
        }

        binding.btnMinus.setOnClickListener {
            val currentPiece = binding.YemekAdet.text.toString().toIntOrNull() ?: 0
            binding.YemekAdet.setText((currentPiece-1).toString())
        }

        binding.AddToBasket.setOnClickListener {
            Log.d("DetayFragment", "BUTTON BASILDI!")
            val adet = binding.YemekAdet.text.toString().toIntOrNull() ?: 1
            if (adet > 0) {
                viewModel.sepeteYemekEkle(
                    secilenYemek.yemek_adi,
                    secilenYemek.yemek_resim_adi,
                    secilenYemek.yemek_fiyat,
                    adet,
                    "Talha_Kasikci"
                )
                Toast.makeText(requireContext(), "Ürün sepete eklendi!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Geçerli bir adet giriniz!", Toast.LENGTH_SHORT).show()
            }
        }




        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner) { sepettekiYemekler ->
            if (sepettekiYemekler.isNotEmpty()) {
                Log.e("sepettekiYemekler", sepettekiYemekler.toString())
                Toast.makeText(
                    requireContext(), 
                    "Sepette ${sepettekiYemekler.size} ürün var", 
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Log.e("sepettekiYemekler", "sepette hiç yemek yok")
                Toast.makeText(requireContext(), "Sepette hiç ürün yok", Toast.LENGTH_SHORT).show()
            }
        }

        // Hata mesajlarını observe et
        viewModel.hataMesaji.observe(viewLifecycleOwner) { hata ->
            Toast.makeText(requireContext(), hata, Toast.LENGTH_LONG).show()
            Log.e("DetayFragment", hata)
        }
    }


}