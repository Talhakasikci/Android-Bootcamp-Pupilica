package com.talhakasikci.odev6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.talhakasikci.odev6.databinding.SubImageRowItemBinding

class SubImageAdapter(private val images: List<Int>) : RecyclerView.Adapter<SubImageAdapter.SubImageViewHolder>() {

    class SubImageViewHolder(private val binding: SubImageRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageRes: Int) {
            binding.subImage.setImageResource(imageRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubImageViewHolder {
        val binding = SubImageRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount() = images.size
} 