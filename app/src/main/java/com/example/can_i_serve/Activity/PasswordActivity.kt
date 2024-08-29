package com.example.can_i_serve.Activity

import RegisterDetails
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        val data:RegisterDetails = intent.getSerializableExtra("register-details") as RegisterDetails


        binding.registerBtn.setOnClickListener {
           verifyPassword(data)
        }
    }

    private fun verifyPassword(data: RegisterDetails) {
        val password=binding.password.text.toString().trim()
        val confirmPassword=binding.confirmPassword.text.toString().trim()
        utils.print("password $password and $confirmPassword")
        if(password==confirmPassword){
            data.password=password
            utils.print("enter in if condition")
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            utils.showAlert(this,"Please check the password")
        }
    }
}