package com.talhakasikci.odev7.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.talhakasikci.odev7.databinding.FragmentDetaySayfaBinding
import com.talhakasikci.odev7.ui.viewmodel.TodoViewModel

class DetaySayfaFragment : Fragment() {
    private var _binding: FragmentDetaySayfaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TodoViewModel by viewModels()
    private val args: DetaySayfaFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetaySayfaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.getTodoById(args.todoId).observe(viewLifecycleOwner) { todo ->
            todo?.let {
                binding.editTextName.setText(it.name)
                binding.editTextContent.setText(it.content)
            }
        }
    }

    private fun setupListeners() {
        binding.buttonGuncelle.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val content = binding.editTextContent.text.toString()

            if (name.isNotEmpty() && content.isNotEmpty()) {
                viewModel.getTodoById(args.todoId).observe(viewLifecycleOwner) { todo ->
                    todo?.let {
                        val updatedTodo = it.copy(name = name, content = content)
                        viewModel.updateTodo(updatedTodo)
                        findNavController().navigateUp()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 