package com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.UI.View

import MainFragmentAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.R
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.UI.viewModel.YemeklerViewModel
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private val viewModel: YemeklerViewModel by viewModels()
    private lateinit var adapter :MainFragmentAdapter
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainRV.layoutManager = GridLayoutManager(requireContext(),2)
        adapter = MainFragmentAdapter(emptyList()){secilenYemek->
            Log.d("secilenYemek", secilenYemek.yemek_adi)


        }
        binding.mainRV.adapter = adapter


        viewModel.tumYemekleriGetir()

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){yemekler->
            Log.d("yemeklerListesi", yemekler.toString())
            adapter = MainFragmentAdapter(yemekler) { secilenYemek ->
                Log.d("secilenYemek", secilenYemek.yemek_adi)

                val bundle = Bundle().apply {
                    putParcelable("secilenYemek", secilenYemek)
                }
                findNavController().navigate(R.id.action_mainFragment_to_detayFragment, bundle)

            }
            binding.mainRV.adapter = adapter

            binding.autoCompleteTextView.addTextChangedListener {
                val arananYemek = yemekler.filter { yemek->
                    yemek.yemek_adi.contains(it.toString(),ignoreCase = true)
                }

                adapter = MainFragmentAdapter(arananYemek) {
                }

                binding.mainRV.adapter = adapter
            }



        }

        viewModel.hataMesaji.observe(viewLifecycleOwner){hata->
            Log.d("hataMesaji", hata)
        }
    }


}