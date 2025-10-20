package com.example.myapplication_test1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication_test1.databinding.FragmentLoginBinding  // Правильный импорт

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null  // Правильный тип
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextScreenButton.setOnClickListener {
            viewModel.login(binding.editTextLogin.text.toString(), binding.editTextPassword.text.toString())
        }

        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is LoginResult.Success -> {
                    Toast.makeText(requireContext(), "Успешный вход", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_secondFragment)
                }
                is LoginResult.Error -> {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
                LoginResult.EmptyFields -> {
                    Toast.makeText(requireContext(), "Введите логин и пароль", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
