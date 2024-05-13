package com.ztktsn.moviego

import android.content.Intent
import com.ztktsn.moviego.ui.HomePage
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ztktsn.moviego.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, HomePage())
                .commit()
        }

        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        binding.createAcc.setOnClickListener {
//      s      val intent = Intent(this, createAccount::class.java)
//            startActivity(intent)
//
//
//        }
    }
}