package com.example.can_i_serve.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.can_i_serve.databinding.OtpScreenActivityBinding
import com.example.can_i_serve.databinding.PasswordScreenActivityBinding

class PasswordActivity:AppCompatActivity() {
    private lateinit var binding: PasswordScreenActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=PasswordScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener {
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}