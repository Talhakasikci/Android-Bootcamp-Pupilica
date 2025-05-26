package com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.R
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.models.sepet_yemekler
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.databinding.SepetItemRowBinding

class SepetFragmentAdapter(
        private val sepetListesi: List<sepet_yemekler>,
        private val onDeleteClick:(sepet_yemekler)->Unit) :RecyclerView.Adapter<SepetFragmentAdapter.ViewHolder>() {

    fun getSepetListesi(): List<sepet_yemekler> = sepetListesi

    class ViewHolder(private val binding:SepetItemRowBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(sepetYemekler: sepet_yemekler, onDeleteClick: (sepet_yemekler) -> Unit) {
            binding.YemekIsmiTextViewRV.text = sepetYemekler.yemek_adi
            binding.YemekFiyatTextViewRV.text = "${sepetYemekler.yemek_fiyat} TL"
            binding.YemekAdetTextViewRV.text = "Adet: ${sepetYemekler.yemek_siparis_adet}"
            binding.YemekToplamFiyatTextViewRV.text = "Toplam Fiyat: ${sepetYemekler.yemek_fiyat.toInt() * sepetYemekler.yemek_siparis_adet.toInt()} TL"
            val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemekler.yemek_resim_adi}"
            Glide.with(binding.root.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageViewSepet)
            
            binding.deleteButton.setOnClickListener {
                onDeleteClick(sepetYemekler)
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SepetItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sepetListesi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sepetYemekler = sepetListesi[position]
        holder.bind(sepetYemekler,onDeleteClick)

    }
}