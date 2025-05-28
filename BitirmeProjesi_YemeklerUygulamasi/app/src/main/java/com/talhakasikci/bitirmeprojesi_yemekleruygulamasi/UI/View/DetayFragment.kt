package com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.UI.View

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.R
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.UI.viewModel.YemeklerViewModel
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.models.Yemek
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.databinding.FragmentDetayBinding


class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private val viewModel: YemeklerViewModel by viewModels()
    private var quantity = 1

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
        binding.TotalPriceTextView.text = "Toplam: ${secilenYemek.yemek_fiyat.toInt()} TL"
        updateQuantityDisplay()

        // Quantity selector buton click listener'ları
        binding.btnPlus.setOnClickListener {
            if (quantity < 99) { // Maksimum 99 adet
                quantity++
                updateQuantityDisplay()
            }
            binding.TotalPriceTextView.text ="Toplam: ${secilenYemek.yemek_fiyat.toInt() * quantity} TL"
        }

        binding.btnMinus.setOnClickListener {
            if (quantity > 1) { // Minimum 1 adet
                quantity--
                updateQuantityDisplay()
            }
            binding.TotalPriceTextView.text ="Toplam: ${secilenYemek.yemek_fiyat.toInt() * quantity} TL"
        }

        binding.AddToBasket.setOnClickListener {
            Log.d("DetayFragment", "BUTTON BASILDI!")
            Log.d("DetayFragment", "Sepete ekleme başlatılıyor: ${secilenYemek.yemek_adi}, adet: $quantity")
            
            viewModel.sepeteYemekEkle(
                secilenYemek.yemek_adi,
                secilenYemek.yemek_resim_adi,
                secilenYemek.yemek_fiyat,
                quantity,
                "Talha_Kasikci"
            )
            
            Toast.makeText(
                requireContext(),
                "$quantity adet ${secilenYemek.yemek_adi} sepete eklendi!",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_detayFragment_to_sepetFragment)
        }



        // Sepet yemekleri için doğru LiveData'yı observe et
        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner) { sepettekiYemekler ->
            Log.d("DetayFragment", "Sepetteki yemekler geldi: ${sepettekiYemekler.size} adet")
            if (sepettekiYemekler.isNotEmpty()) {
                Log.e("sepettekiYemekler", sepettekiYemekler.toString())
            } else {
                Log.e("sepettekiYemekler", "sepette hiç yemek yok")
            }
        }

        // Hata mesajlarını observe et
        viewModel.hataMesaji.observe(viewLifecycleOwner) { hata ->
            Log.e("DetayFragment", "Hata oluştu: $hata")
        }

        // Floating Action Button - Sepete Git

    }

    private fun updateQuantityDisplay() {
        binding.YemekAdet.text = quantity.toString()
        
        // Butonların durumunu güncelle
        binding.btnMinus.isEnabled = quantity > 1
        binding.btnPlus.isEnabled = quantity < 99
        
        // Disabled butonların görsel durumunu ayarla
        binding.btnMinus.alpha = if (quantity > 1) 1.0f else 0.5f
        binding.btnPlus.alpha = if (quantity < 99) 1.0f else 0.5f
    }


}