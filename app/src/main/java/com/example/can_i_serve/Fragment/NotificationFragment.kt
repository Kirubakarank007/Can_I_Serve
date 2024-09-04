package com.example.can_i_serve.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.can_i_serve.R
import com.example.can_i_serve.databinding.ProviderNotificationFragmentBinding
import com.example.can_i_serve.databinding.SeekerNotificationFragmentBinding
import com.example.can_i_serve.utils

class NotificationFragment(private val layoutId:Int):Fragment() {
    private var binding: ViewBinding? = null
    private val _binding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Check which layout to bind
        binding = when (layoutId) {
            R.layout.seeker_notification_fragment -> SeekerNotificationFragmentBinding.inflate(inflater, container, false)
            R.layout.provider_notification_fragment -> ProviderNotificationFragmentBinding.inflate(inflater, container, false)
            else -> throw IllegalArgumentException("Invalid layout")
        }

        // You can now use the appropriate binding
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utils.print("enter in notification fragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}