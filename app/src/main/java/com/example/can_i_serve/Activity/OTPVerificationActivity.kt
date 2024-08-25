package com.example.can_i_serve.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.can_i_serve.databinding.OtpScreenActivityBinding

class OTPVerificationActivity: AppCompatActivity() {
    private lateinit var binding:OtpScreenActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=OtpScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.verifyBtn.setOnClickListener {
            val intent= Intent(this,PasswordActivity::class.java)
            startActivity(intent)
        }

    }

}