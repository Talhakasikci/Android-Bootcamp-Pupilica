package com.talhakasikci.odev7.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.talhakasikci.odev7.data.entity.Todo
import com.talhakasikci.odev7.databinding.FragmentKayitSayfaBinding
import com.talhakasikci.odev7.ui.viewmodel.TodoViewModel

class KayitSayfaFragment : Fragment() {
    private var _binding: FragmentKayitSayfaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKayitSayfaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.buttonKaydet.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val content = binding.editTextContent.text.toString()

            if (name.isNotEmpty() && content.isNotEmpty()) {
                val todo = Todo(name = name, content = content)
                viewModel.insertTodo(todo)
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 