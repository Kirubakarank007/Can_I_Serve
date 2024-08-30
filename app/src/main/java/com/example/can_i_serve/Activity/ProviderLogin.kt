package com.example.can_i_serve.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.can_i_serve.databinding.AlertDialogeBinding
import com.example.can_i_serve.databinding.ProviderProfileActivityBinding

class ProviderLogin:AppCompatActivity() {
    private lateinit var binding: ProviderProfileActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding=ProviderProfileActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.home.setOnClickListener {
            binding.homeBar.visibility= View.VISIBLE
            binding.notificationBar.visibility=View.INVISIBLE
            binding.profileBar.visibility=View.INVISIBLE

        }

        binding.notification.setOnClickListener {
            binding.homeBar.visibility= View.INVISIBLE
            binding.notificationBar.visibility=View.VISIBLE
            binding.profileBar.visibility=View.INVISIBLE
        }

        binding.profile.setOnClickListener {
            binding.homeBar.visibility= View.INVISIBLE
            binding.notificationBar.visibility=View.INVISIBLE
            binding.profileBar.visibility=View.VISIBLE
        }
    }
}