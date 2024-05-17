package com.ztktsn.moviego.RegistrationForms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.ztktsn.moviego.ui.HomeFragment
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginButton: Button = view.findViewById(R.id.loginButton)
        val createAccountText: Button = view.findViewById(R.id.createAccountText)
        private fun replaceFragment(fragment: Fragment) {
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, fragment)
            fragmentTransaction.commit()
        }

        loginButton.setOnClickListener {
            replaceFragment(HomeFragment())
        }

        createAccountText.setOnClickListener {
            replaceFragment(Registrations())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailInput: EditText = view.findViewById(R.id.emailInput)
        val passwordInput: EditText = view.findViewById(R.id.passwordInput)
        val loginButton: Button = view.findViewById(R.id.loginButton)
        val createAccountText: Button = view.findViewById(R.id.createAccountText)

        loginButton.setOnClickListener {
            val username = emailInput.text.toString()
            val password = passwordInput.text.toString()

            // Проверка учетных данных (простейшая реализация)
            if (username == "user" && password == "password") {
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                // Переход к следующему фрагменту или активности после успешного входа
            } else {
                Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }

    }
}