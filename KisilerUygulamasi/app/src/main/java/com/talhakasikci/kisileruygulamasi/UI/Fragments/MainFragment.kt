// app/src/main/java/com/talhakasikci/kisileruygulamasi/UI/Fragments/MainFragment.kt
package com.talhakasikci.kisileruygulamasi.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.talhakasikci.kisileruygulamasi.R
import com.talhakasikci.kisileruygulamasi.UI.adapter.adapter
import com.talhakasikci.kisileruygulamasi.UI.viewModel.AnaSayfaViewModel
import com.talhakasikci.kisileruygulamasi.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: AnaSayfaViewModel by viewModels()
    private lateinit var adapter: adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // RecyclerView ayarları
        binding.RVmain.layoutManager = LinearLayoutManager(requireContext())
        adapter = adapter(emptyList()) { clickedKisi ->
            // Navigate to edit fragment with the clicked contact
            val action = MainFragmentDirections.actionMainFragmentToEditPersonFragment(clickedKisi)
            view.findNavController().navigate(action)
        }
        binding.RVmain.adapter = adapter
        
        // Observe the contacts LiveData
        viewModel.kisiler.observe(viewLifecycleOwner) { kisiler ->
            adapter.setData(kisiler)
        }
        
        // Load contacts when fragment is created
        viewModel.kisileriYukle()
        
        binding.floatingActionButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToAddPersonFragmet()
            view.findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh the list when returning to this fragment
        viewModel.kisileriYukle()
    }
}