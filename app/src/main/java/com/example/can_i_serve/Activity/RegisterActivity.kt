package com.example.can_i_serve.Activity

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.can_i_serve.Fragment.OTPVerificationFragment
import com.example.can_i_serve.R
import com.example.can_i_serve.databinding.ActivityMainBinding
import com.example.can_i_serve.databinding.RegisterActivityBinding
import com.example.can_i_serve.utils

class RegisterActivity:AppCompatActivity() {
    private lateinit var binding: RegisterActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=RegisterActivityBinding.inflate(layoutInflater)
        binding.root.setOnClickListener {
            utils.hideKeyboard(it,this)
        }
        setContentView(binding.root)
        val roles = listOf("Seeker", "Provider")
        val roleAdapter = ArrayAdapter(this, R.layout.drop_down , roles)
        binding.role.setAdapter(roleAdapter)
        binding.role.setOnClickListener {
            binding.role.showDropDown()
        }
        binding.sendotpBtn.setOnClickListener {
            binding.fragmentContainer.visibility= View.VISIBLE
            loadFragment(OTPVerificationFragment())
        }
    }


    fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null) // Adds to back stack so that the user can navigate back
        transaction.commit()
    }
}