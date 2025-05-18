package com.talhakasikci.odev7.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.talhakasikci.odev7.databinding.FragmentAnaSayfaBinding
import com.talhakasikci.odev7.ui.adapter.TodoAdapter
import com.talhakasikci.odev7.ui.viewmodel.TodoViewModel

class AnaSayfaFragment : Fragment() {
    private var _binding: FragmentAnaSayfaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TodoViewModel by viewModels()
    private lateinit var adapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnaSayfaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        setupListeners()
    }

    private fun setupRecyclerView() {
        adapter = TodoAdapter(
            onItemClick = { todo ->
                val action = AnaSayfaFragmentDirections.actionAnaSayfaFragmentToDetaySayfaFragment(todo.id)
                findNavController().navigate(action)
            },
            onDeleteClick = { todo ->
                viewModel.deleteTodo(todo)
            }
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@AnaSayfaFragment.adapter
        }
    }

    private fun setupObservers() {
        viewModel.allTodos.observe(viewLifecycleOwner) { todos ->
            adapter.submitList(todos)
        }
    }

    private fun setupListeners() {
        binding.fabAddTodo.setOnClickListener {
            findNavController().navigate(AnaSayfaFragmentDirections.actionAnaSayfaFragmentToKayitSayfaFragment())
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchTodos(it).observe(viewLifecycleOwner) { todos ->
                    adapter.submitList(todos)
                }}
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchTodos(it).observe(viewLifecycleOwner) { todos ->
                    adapter.submitList(todos)
                }}
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 