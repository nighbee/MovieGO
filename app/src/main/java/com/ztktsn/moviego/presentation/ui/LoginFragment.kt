package com.ztktsn.moviego.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.fragment.findNavController
import com.ztktsn.moviego.R

import com.ztktsn.moviego.databinding.FragmentLoginBinding

import com.ztktsn.moviego.data.userData.UserData


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val login = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            UserData(requireContext()).setAuthorized()

            findNavController().navigate(R.id.action_login_to_home_fragment)
        }
        binding.createAccountText.setOnClickListener({
            findNavController().navigate(R.id.action_login_to_registration_fragment)
        })
//        private fun login(email: String, password: String) {
//            val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
//            val storedEmail = sharedPreferences.getString("email", null)
//            val storedPassword = sharedPreferences.getString("password", null)
//
//            if (email == storedEmail && password == storedPassword) {
//                Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show()
//                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
//            } else {
//                Toast.makeText(requireContext(), "Invalid Email or Password!", Toast.LENGTH_SHORT).show()
//            }
//        }
    }
}