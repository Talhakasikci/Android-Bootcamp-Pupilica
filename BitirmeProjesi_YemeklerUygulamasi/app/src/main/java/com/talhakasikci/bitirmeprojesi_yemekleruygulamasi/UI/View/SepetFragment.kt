package com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.UI.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.R
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.UI.viewModel.YemeklerViewModel
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.adapters.SepetFragmentAdapter
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.models.sepet_yemekler
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.databinding.FragmentSepetBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.notifyAll


class SepetFragment : Fragment() {
    private lateinit var binding: FragmentSepetBinding
    private val viewModel: YemeklerViewModel by viewModels()
    private lateinit var adapter: SepetFragmentAdapter
    

    private var orijinalSepetListesi: List<sepet_yemekler> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSepetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = SepetFragmentAdapter(emptyList()) { }
        binding.recyclerView.adapter = adapter
        
        // İlk başta boş sepet mesajını göster
        showEmptyMessage()
        
        viewModel.sepettekiYemekleriGetir("Talha_Kasikci")

        // Sepeti Onayla Buton Click Listener
        binding.materialButton.setOnClickListener {
            if( orijinalSepetListesi.isNotEmpty()) {
                lifecycleScope.launch {
                    showSuccessAnimation()
                    delay(3000)
                    clearBasketAfterOrder()
                }

            }else{
                // Sepet boşsa kullanıcıya bilgi ver

                return@setOnClickListener
            }
        }

        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner) { sepetYemekler ->

            orijinalSepetListesi = sepetYemekler
            
            if (sepetYemekler.isNotEmpty()) {
                val birlestir = sepetBirlestir(sepetYemekler)

                binding.recyclerView.visibility = View.VISIBLE
                binding.emptyTextView.visibility = View.GONE
                

                adapter = SepetFragmentAdapter(birlestir) { silinecekYemek ->
                    sepettenSil(silinecekYemek)
                }
                binding.recyclerView.adapter = adapter
                
                val toplamTutar = birlestir.sumOf { it.yemek_fiyat.toInt() * it.yemek_siparis_adet.toInt() }
                updateTotalPrice(toplamTutar)
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.emptyTextView.visibility = View.VISIBLE
                updateTotalPrice(0)
            }
        }
    }

    fun sepetBirlestir(sepetYemekler: List<sepet_yemekler>): List<sepet_yemekler> {
        return sepetYemekler
            .groupBy { it.yemek_adi }
            .map { (yemekAdi, ayniYemek) ->
                val yemek = ayniYemek.first()
                val toplamAdet = ayniYemek.sumOf { it.yemek_siparis_adet.toInt() }
                sepet_yemekler(
                    sepet_yemek_id = yemek.sepet_yemek_id,
                    yemek_adi = yemek.yemek_adi,
                    yemek_resim_adi = yemek.yemek_resim_adi,
                    yemek_fiyat = yemek.yemek_fiyat,
                    yemek_siparis_adet = toplamAdet.toString(),
                    kullanici_adi = yemek.kullanici_adi
                )
            }
    }
    

    private fun sepettenSil(silinecekYemek: sepet_yemekler) {

        val mevcutListe = adapter.getSepetListesi().toMutableList()
        
        if (silinecekYemek.yemek_siparis_adet.toInt() > 1) {

            val azaltilmisYemek = silinecekYemek.copy(
                yemek_siparis_adet = (silinecekYemek.yemek_siparis_adet.toInt() - 1).toString()
            )
            val index = mevcutListe.indexOfFirst { it.yemek_adi == silinecekYemek.yemek_adi }
            if (index != -1) {
                mevcutListe[index] = azaltilmisYemek
            }
            

            val ilkKayit = orijinalSepetListesi.firstOrNull { it.yemek_adi == silinecekYemek.yemek_adi }
            ilkKayit?.let {
                viewModel.sepettenYemekSil(it.sepet_yemek_id.toInt(), "Talha_Kasikci")
            }
        } else {

            mevcutListe.removeAll { it.yemek_adi == silinecekYemek.yemek_adi }
            

            val ayniYemekKayitlari = orijinalSepetListesi.filter { 
                it.yemek_adi == silinecekYemek.yemek_adi 
            }
            ayniYemekKayitlari.forEach { kayit ->
                viewModel.sepettenYemekSil(kayit.sepet_yemek_id.toInt(), "Talha_Kasikci")
            }
        }
        
        // UI'ı anında güncelle
        if (mevcutListe.isEmpty()) {
            binding.recyclerView.visibility = View.GONE
            binding.emptyTextView.visibility = View.VISIBLE
            updateTotalPrice(0)
        } else {
            adapter = SepetFragmentAdapter(mevcutListe) { yemek -> sepettenSil(yemek) }
            binding.recyclerView.adapter = adapter
            val yeniToplamTutar = mevcutListe.sumOf { it.yemek_fiyat.toInt() * it.yemek_siparis_adet.toInt() }
            updateTotalPrice(yeniToplamTutar)
        }
        

        binding.root.postDelayed({
            viewModel.sepettekiYemekleriGetir("Talha_Kasikci")
        }, 1000)
    }

    private fun updateTotalPrice(toplamTutar: Int) {
        binding.toplamTutarSepet.text = "${toplamTutar} TL"
    }

    private fun showEmptyMessage() {
        binding.recyclerView.visibility = View.GONE
        binding.emptyTextView.visibility = View.VISIBLE
        updateTotalPrice(0)
    }

    private fun showSuccessAnimation() {
        // Animasyonu göster
        binding.successAnimationView.visibility = View.VISIBLE
        
        // JSON dosyasını yükle (eğer layout'ta tanımlanmamışsa)
        try {
            binding.successAnimationView.setAnimation(R.raw.success_animation)
        } catch (e: Exception) {
            // Eğer dosya bulunamazsa varsayılan animasyon göster
            android.widget.Toast.makeText(requireContext(), "Animasyon dosyası bulunamadı", android.widget.Toast.LENGTH_SHORT).show()
            return
        }
        
        // Animasyonu başlat
        binding.successAnimationView.playAnimation()
        
        // Animasyon bittiğinde overlay'i gizle
        binding.successAnimationView.addAnimatorListener(object : android.animation.Animator.AnimatorListener {
            override fun onAnimationStart(animation: android.animation.Animator) {}
            override fun onAnimationRepeat(animation: android.animation.Animator) {}
            override fun onAnimationCancel(animation: android.animation.Animator) {}
            
            override fun onAnimationEnd(animation: android.animation.Animator) {
                // 1 saniye bekle ve sonra gizle
                binding.root.postDelayed({
                    binding.successAnimationView.visibility = View.GONE
                    
                    // İsteğe bağlı: Ana sayfaya dön veya mesaj göster
                    android.widget.Toast.makeText(
                        requireContext(), 
                        "Siparişiniz başarıyla alındı!", 
                        android.widget.Toast.LENGTH_LONG
                    ).show()
                }, 1000)
            }
        })
    }
    private fun clearBasketAfterOrder(){
    orijinalSepetListesi.forEach { sepetYemek->
        viewModel.sepettenYemekSil(sepetYemek.sepet_yemek_id.toInt(), "Talha_Kasikci")
    }
        orijinalSepetListesi = emptyList()
        showEmptyMessage()
        binding.root.postDelayed({viewModel.sepettekiYemekleriGetir("Talha_Kasikci")}, 1000)
        }
    }
