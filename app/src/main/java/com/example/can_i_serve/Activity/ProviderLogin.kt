package com.example.can_i_serve.Activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.example.can_i_serve.Fragment.HomeFragment
import com.example.can_i_serve.Fragment.NotificationFragment
import com.example.can_i_serve.Fragment.ProfileFragment
import com.example.can_i_serve.R
import com.example.can_i_serve.databinding.ProviderProfileActivityBinding
import com.example.can_i_serve.utils

class ProviderLogin:AppCompatActivity() {
    private lateinit var binding: ProviderProfileActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding=ProviderProfileActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val role=intent.getIntExtra("role",-1)
        utils.print(role.toString())
        if(role==0){
            changeHeaderAndFooter(binding.Header, R.color.dark1)
            changeHeaderAndFooter(binding.footer, R.color.lite1)
            binding.HeaderText.text= getString(R.string.seeker)
        }else{
            changeHeaderAndFooter(binding.Header, R.color.dark)
            changeHeaderAndFooter(binding.footer, R.color.dark)
            binding.HeaderText.text= getString(R.string.provider)
        }
        supportFragmentManager.beginTransaction().apply {
            if (role == 0) {
                replace(R.id.fragmentContainer, HomeFragment(R.layout.seeker_home_fragment), "HOME_FRAGMENT")
            } else {
                replace(R.id.fragmentContainer, HomeFragment(R.layout.provider_home_fragment), "HOME_FRAGMENT_PROVIDER")
            }
        }.commit()

        binding.home.setOnClickListener {
            if(role==0){
                replaceFragment(HomeFragment(R.layout.seeker_home_fragment),"HOME_FRAGMENT")
                utils.print("provider home")
            }else{
                replaceFragment(HomeFragment(R.layout.provider_home_fragment),"HOME_FRAGMENT_PROVIDER")
            }
            binding.homeBar.visibility= View.VISIBLE
            binding.notificationBar.visibility=View.INVISIBLE
            binding.profileBar.visibility=View.INVISIBLE

        }

        binding.notification.setOnClickListener {
            if(role==0){
                utils.print("provider notification")
                replaceFragment(NotificationFragment(R.layout.seeker_notification_fragment),"HOME_NOTIFICATION")
            }else{
                replaceFragment(NotificationFragment(R.layout.provider_notification_fragment),"HOME_NOTIFICATION_PROVIDER")
            }
            binding.homeBar.visibility= View.INVISIBLE
            binding.notificationBar.visibility=View.VISIBLE
            binding.profileBar.visibility=View.INVISIBLE
        }

        binding.profile.setOnClickListener {
            if(role==0){
                utils.print("provider profile")
                replaceFragment(ProfileFragment(R.layout.seeker_profile_fragment),"HOME_PROFILE")
            }else{
                replaceFragment(ProfileFragment(R.layout.provider_profile_fragment),"HOME_PROFILE_PROVIDER")
            }
            binding.homeBar.visibility= View.INVISIBLE
            binding.notificationBar.visibility=View.INVISIBLE
            binding.profileBar.visibility=View.VISIBLE
        }
    }

    private fun changeHeaderAndFooter(header: LinearLayout, color: Int) {
        val linearLayout = header

        // Get the background drawable
        val drawable: Drawable? = linearLayout.background

        // Use DrawableCompat to apply the tint to the vector drawable
        drawable?.let {
            // Wrap the drawable to be able to tint it (if needed)
            val wrappedDrawable = DrawableCompat.wrap(it)

            // Set the tint color (replace 'R.color.newColor' with your desired color resource)
            DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(this, color))

            // Apply the modified drawable back to the LinearLayout
            linearLayout.background = wrappedDrawable
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()

        // Replace the fragment
        transaction.replace(R.id.fragmentContainer, fragment, tag)

        // Commit the transaction
        transaction.commit()
    }


}