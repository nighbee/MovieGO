package com.ztktsn.moviego

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ztktsn.moviego.databinding.ActivityTestBinding
import com.ztktsn.moviego.userData.UserData

class testActivity : AppCompatActivity() {

        private lateinit var binding: ActivityTestBinding
        private lateinit var navController: NavController

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityTestBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setupNavigation()
        }

        private fun setupNavigation() {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController

            val navGraph = navController.navInflater.inflate(R.navigation.navigation)
            when {
                UserData(this).isAuthorized() -> {
                    navGraph.setStartDestination(R.id.homeFragment)
                }

                !UserData(this).isAuthorized() -> {
                    navGraph.setStartDestination(R.id.homeFragment)
                }
            }
            navController.graph = navGraph
        }

        override fun onBackPressed() {
            super.onBackPressed()
        }
    }
