package com.ztktsn.moviego.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ztktsn.moviego.R
import com.ztktsn.moviego.databinding.FragmentAccountBinding

class accountFragment : Fragment() {

    private val _binding: FragmentAccountBinding?=null
    private val binding get()= _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bottomNavigationView = binding?.bottomNavigation
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }

    }


        private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.account -> {
                    // No need to load HomeFragment again if already on the home page
                    return@OnNavigationItemSelectedListener true
                }
                R.id.saved -> {
                    loadFragment(savedFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.home -> {
                    loadFragment(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
                else -> return@OnNavigationItemSelectedListener false
            }
        }
    private fun loadFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.account, fragment)
            .addToBackStack(null)
            .commit()
    }


}