package com.example.can_i_serve.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.can_i_serve.databinding.ProfileFragmentBinding

class ProfileFragment:Fragment() {
    private lateinit var binding:ProfileFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= ProfileFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}