package com.example.can_i_serve.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.can_i_serve.databinding.LoginActivityBinding
import com.example.can_i_serve.databinding.PasswordScreenActivityBinding

class LoginActivity:AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}