package com.example.can_i_serve.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.can_i_serve.Database.AppDatabase
import com.example.can_i_serve.Database.Dao.CanIServeDao
import com.example.can_i_serve.Database.DataClass.RegisterDetailsDb
import com.example.can_i_serve.databinding.LoginActivityBinding
import com.example.can_i_serve.utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity:AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

            val db = AppDatabase.getDatabase(this@LoginActivity).canIServeDao()


        binding.loginBtn.setOnClickListener {
            checkUser(db)
        }

    }

    private fun checkUser(db: CanIServeDao) {
            // Launch a coroutine in the lifecycle scope to avoid blocking the main thread
            lifecycleScope.launch {
                // Perform the database query in the IO thread
                val phoneNumberText = binding.phone.text.toString()

                // Switch to IO dispatcher for the database operation
                val user = withContext(Dispatchers.IO) {
                    db.getUserByPhoneNumber(phoneNumberText)
                }

                val passwordText = binding.password.text.toString()

                // Now check the result on the Main thread
                if (user?.password == passwordText) {
                    if (user.role == 0) {
                        val intent = Intent(this@LoginActivity, ProviderLogin::class.java)
                        intent.putExtra("role",0)
                        startActivity(intent)
                    }else{
                        val intent=Intent(this@LoginActivity,SeekerLogin::class.java)
                        intent.putExtra("role",1)
                        startActivity(intent)
                    }
                } else {
                    utils.showAlert(this@LoginActivity, "Invalid credentials")
                }
            }
    }

}