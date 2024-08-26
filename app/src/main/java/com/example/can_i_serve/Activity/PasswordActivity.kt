package com.example.can_i_serve.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.can_i_serve.databinding.OtpScreenActivityBinding
import com.example.can_i_serve.databinding.PasswordScreenActivityBinding
import com.example.can_i_serve.utils

class PasswordActivity:AppCompatActivity() {
    private lateinit var binding: PasswordScreenActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=PasswordScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.setOnClickListener {
            utils.hideKeyboard(it,this)
        }

        binding.registerBtn.setOnClickListener {
           verifyPassword()
        }
    }

    private fun verifyPassword() {
        val password=binding.password.text
        val confirmPassword=binding.confirmPassword.text
        if(password==confirmPassword){
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}