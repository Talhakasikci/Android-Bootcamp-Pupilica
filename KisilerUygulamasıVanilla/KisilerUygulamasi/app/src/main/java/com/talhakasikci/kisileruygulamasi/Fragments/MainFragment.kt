package com.talhakasikci.kisileruygulamasi.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.talhakasikci.kisileruygulamasi.R
import com.talhakasikci.kisileruygulamasi.data.Entity.Kisiler
import com.talhakasikci.kisileruygulamasi.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToAddPersonFragmet()
            view.findNavController().navigate(action)
        }

        binding.buttonDetay.setOnClickListener {
            val kisi1 = Kisiler(1, "Talha", "0532 123 45 67")
            val action = MainFragmentDirections.actionMainFragmentToEditPersonFragment(kisi1)
            view.findNavController().navigate(action)
        }
    }


}