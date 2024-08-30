package com.example.can_i_serve.Activity

import RegisterDetails
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.can_i_serve.databinding.OtpScreenActivityBinding
import com.example.can_i_serve.utils
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class OTPVerificationActivity: AppCompatActivity() {
    private lateinit var binding:OtpScreenActivityBinding
    private lateinit var mAuth:FirebaseAuth
    lateinit var verificationCode:String
    lateinit var resendingToken:PhoneAuthProvider.ForceResendingToken
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=OtpScreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.setOnClickListener {
            utils.hideKeyboard(it,this)
        }

        mAuth=FirebaseAuth.getInstance()
        val data:RegisterDetails = intent.getSerializableExtra("register_details") as RegisterDetails
        Log.d("##OTP",data.toString())

        sendOtp("+"+data.countryCode+data.phone,false)
//       testing firebase is connected or not
//        val value: MutableMap<String, String> = HashMap()
//        FirebaseFirestore.getInstance().collection("test").add(value)
        binding.verifyBtn.setOnClickListener {
            val otp=binding.otp1.text.toString()+binding.otp2.text+binding.otp3.text+binding.otp4.text+binding.otp5.text+binding.otp6.text
            val credentials=PhoneAuthProvider.getCredential(verificationCode,otp)
            utils.print("OTP $otp")
            verifyOtp(credentials,data)
        }

    }

    private fun verifyOtp(credentiale: PhoneAuthCredential, data: RegisterDetails) {
        mAuth.signInWithCredential(credentiale).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this, PasswordActivity::class.java)
                intent.putExtra("register-details",data)
                startActivity(intent)
                finish()
            } else {
                utils.showAlert(this,"please enter valid OTP")
            }
        }
    }


    private fun sendOtp(phone: String?, isResend: Boolean) {
        val builder = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(phone!!)   // Set the phone number for verification
            .setTimeout(60L, TimeUnit.SECONDS)  // Set timeout duration
            .setActivity(this)         // Make sure to set the current activity
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                    verifyOtp(credentiale)
                    utils.toastMessage(this@OTPVerificationActivity, "Verification Completed")
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Log.e("##OTP", "Verification failed: ${e.message}")
                    utils.toastMessage(this@OTPVerificationActivity, "OTP verification failed: ${e.localizedMessage}")
                }

                override fun onCodeSent(OTP: String, token: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(OTP, token)
                    verificationCode = OTP
                    resendingToken = token
                    utils.toastMessage(this@OTPVerificationActivity, "OTP sent successfully")
                }
            })

        // Handle resending of the OTP
        if (isResend) {
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build())
        } else {
            PhoneAuthProvider.verifyPhoneNumber(builder.build())
        }
    }


}