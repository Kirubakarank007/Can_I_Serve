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
import com.example.can_i_serve.databinding.SeekerProfileActivityBinding

class SeekerLogin:AppCompatActivity() {
    private lateinit var binding: SeekerProfileActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding= SeekerProfileActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeHeaderAndFooter(binding.Header,R.color.dark1)
        changeHeaderAndFooter(binding.footer,R.color.lite1)



        // Add all fragments but hide them initially, except the homeFragment
        supportFragmentManager.beginTransaction().apply {
            add(binding.fragmentContainer, HomeFragment(), "HOME_FRAGMENT")
        }.commit()
        binding.home.setOnClickListener {
            replaceFragment(HomeFragment(),"HOME_FRAGMENT")
            binding.homeBar.visibility= View.VISIBLE
            binding.notificationBar.visibility= View.INVISIBLE
            binding.profileBar.visibility= View.INVISIBLE

        }

        binding.notification.setOnClickListener {
            replaceFragment(NotificationFragment(),"NOTIFICATION_FRAGMENT")
            binding.homeBar.visibility= View.INVISIBLE
            binding.notificationBar.visibility= View.VISIBLE
            binding.profileBar.visibility= View.INVISIBLE
        }

        binding.profile.setOnClickListener {
            replaceFragment(ProfileFragment(),"PROFILE_FRAGMENT")
            binding.homeBar.visibility= View.INVISIBLE
            binding.notificationBar.visibility= View.INVISIBLE
            binding.profileBar.visibility= View.VISIBLE
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