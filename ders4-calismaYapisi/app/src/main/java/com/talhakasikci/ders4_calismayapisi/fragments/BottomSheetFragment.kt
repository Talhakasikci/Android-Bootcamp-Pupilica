package com.talhakasikci.ders4_calismayapisi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.talhakasikci.ders4_calismayapisi.R
import com.talhakasikci.ders4_calismayapisi.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentBottomSheetBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)

        binding.imageViewPaylas.setOnClickListener {
            Toast.makeText(requireContext(), "Paylaş tıklandı", Toast.LENGTH_SHORT).show()
        }



        return binding.root

    }


}