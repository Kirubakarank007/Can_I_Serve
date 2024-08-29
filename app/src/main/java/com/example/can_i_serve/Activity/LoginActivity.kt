package com.example.can_i_serve.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.can_i_serve.Database.AppDatabase
import com.example.can_i_serve.Database.DataClass.RegisterDetailsDb
import com.example.can_i_serve.databinding.LoginActivityBinding
import com.example.can_i_serve.utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity:AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding
    private lateinit var  phoneNumberList:MutableList<String>
    private lateinit var passwordList:MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(this@LoginActivity).canIServeDao()
            withContext(Dispatchers.IO) {
                phoneNumberList = phoneNumber(db.getAllUsers())
                passwordList = password(db.getAllUsers())
            }
        }

        binding.loginBtn.setOnClickListener {
            checkUser()
        }

    }

    private fun checkUser() {
        val phoneNumberText=binding.phone.text.toString()
        val passwordText=binding.password.text.toString()
        if(phoneNumberList.contains(phoneNumberText)){
            val phoneIndex=phoneNumberList.indexOf(phoneNumberText)
            if(passwordText==passwordList[phoneIndex]){
                val intent=Intent(this,OTPVerificationActivity::class.java)
                startActivity(intent)
            }
            else{
                utils.showAlert(this,"Password is Incorrect")
            }
        }else{
            utils.showAlert(this,"Please enter register Phone number")
        }
    }

    private fun password(db: List<RegisterDetailsDb>): MutableList<String> {
        var list= mutableListOf<String>()
        for(i in 0..db.size.minus(1)){
            list.add(db[i].password.toString())
        }
        return list
    }

    private fun phoneNumber(db: List<RegisterDetailsDb>): MutableList<String> {
        var list= mutableListOf<String>()
        for(i in 0..db.size.minus(1)){
            list.add(db[i].phone.toString())
        }
        return list
    }
}