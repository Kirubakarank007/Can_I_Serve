package com.example.can_i_serve.Activity

import RegisterDetails
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.can_i_serve.Database.AppDatabase
import com.example.can_i_serve.Database.DataClass.RegisterDetailsDb
import com.example.can_i_serve.databinding.PasswordScreenActivityBinding
import com.example.can_i_serve.utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PasswordActivity:AppCompatActivity() {
    private lateinit var binding: PasswordScreenActivityBinding
    private lateinit var db:AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=PasswordScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.setOnClickListener {
            utils.hideKeyboard(it,this)
        }

        val data:RegisterDetails = intent.getSerializableExtra("register-details") as RegisterDetails
        db=AppDatabase.getDatabase(this)

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
            storeInDb(data)
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            utils.showAlert(this,"Please check the password")
        }
    }

    private fun storeInDb(data: RegisterDetails) {
        val insertData=RegisterDetailsDb(
            name = data.name,
             age=data.age,
             location=data.location,
             email=data.email,
             phone=data.phone,
             role=data.role,
             countryCode=data.countryCode,
             password=data.password
        )
        CoroutineScope(Dispatchers.IO).launch {
            db.canIServeDao().insert(insertData)
        }
    }
}