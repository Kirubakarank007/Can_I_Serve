package com.example.can_i_serve.Activity

import RegisterDetails
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.can_i_serve.databinding.OtpScreenActivityBinding
import java.io.Serializable

class OTPVerificationActivity: AppCompatActivity() {
    private lateinit var binding:OtpScreenActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=OtpScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data:RegisterDetails = intent.getSerializableExtra("register_details") as RegisterDetails
        Log.d("##OTP",data.toString())
        binding.verifyBtn.setOnClickListener {
            val intent= Intent(this,PasswordActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}