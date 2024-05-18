package com.ztktsn.moviego.ui
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ztktsn.moviego.R
import com.ztktsn.moviego.adapter.RegisterRequest
import com.ztktsn.moviego.databinding.FragmentLoginBinding
import com.ztktsn.moviego.databinding.FragmentRegistrationBinding
import com.ztktsn.moviego.network.ApiClient.instance
import com.ztktsn.moviego.userData.UserData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createAccount.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val confirmPassword = binding.passwordRepeatInput.text.toString()

            if (email.isNotBlank() && password.isNotBlank() && confirmPassword == password) {
                val registerRequest = RegisterRequest(email, password, confirmPassword)
                instance.register(registerRequest).enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            saveUserToPreferences(email, password)
                            Toast.makeText(requireContext(), "Registration Successful!", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_registration_to_login)
                        } else {
                            Toast.makeText(requireContext(), "Registration Failed!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(requireContext(), "Please fill all fields correctly.", Toast.LENGTH_SHORT).show()
            }
        }
        binding.haveLogin.setOnClickListener({
            findNavController().navigate(R.id.action_registration_to_login)
        })
    }

    private fun saveUserToPreferences(email: String, password: String) {
        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
    }
}