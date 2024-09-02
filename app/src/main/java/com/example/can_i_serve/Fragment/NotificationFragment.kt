package com.example.can_i_serve.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.can_i_serve.databinding.NotificationFragmentBinding

class NotificationFragment:Fragment() {
    private lateinit var binding:NotificationFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=NotificationFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}