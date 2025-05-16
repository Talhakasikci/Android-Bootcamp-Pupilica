package com.talhakasikci.kisileruygulamasi.UI.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.talhakasikci.kisileruygulamasi.data.Entity.Kisiler
import com.talhakasikci.kisileruygulamasi.databinding.RvItemRowBinding


    // app/src/main/java/com/talhakasikci/kisileruygulamasi/UI/Adapters/KisilerAdapter.kt

    class adapter(
        private var kisilerList: List<Kisiler>,
        private val onItemClick: (Kisiler) -> Unit
    ) : RecyclerView.Adapter<adapter.KisilerViewHolder>() {

        inner class KisilerViewHolder(val binding: RvItemRowBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(kisi: Kisiler) {
                binding.nameTV.text = kisi.kisi_ad
                binding.noTV.text = kisi.kisi_tel
                binding.root.setOnClickListener {
                    onItemClick(kisi)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KisilerViewHolder {
            val binding = RvItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return KisilerViewHolder(binding)
        }

        override fun onBindViewHolder(holder: KisilerViewHolder, position: Int) {
            holder.bind(kisilerList[position])
        }

        override fun getItemCount() = kisilerList.size

        fun setData(newList: List<Kisiler>) {
            kisilerList = newList
            notifyDataSetChanged()
        }
    }
